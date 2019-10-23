package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/9 21:55
 * @description：后端用户业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.annotation.DataScope;
import com.alina.bootrpc.common.core.constant.UserConstants;
import com.alina.bootrpc.common.core.exception.BusinessException;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.core.utils.security.Md5Utils;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysConfigService;
import com.alina.bootrpc.system.facade.ISysUserService;
import com.alina.bootrpc.system.mapper.SysPostMapper;
import com.alina.bootrpc.system.mapper.SysRoleMapper;
import com.alina.bootrpc.system.mapper.SysUserMapper;
import com.alina.bootrpc.system.model.SysPost;
import com.alina.bootrpc.system.model.SysRole;
import com.alina.bootrpc.system.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(version="1.0.0")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysPostMapper postMapper;

	@Autowired
	private SysUserMapper userMapper;

	@Autowired
	private ISysConfigService configService;

	@Override
	public SysUser selectUserByLoginName(String userName) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("loginName",userName);
		return queryOneByExample(example);
	}

	@Override
	public SysUser selectUserByPhoneNumber(String phonenumber) {
		Map<String , Object> params = new HashMap<>(16);
		params.put("phonenumber",phonenumber);
		return queryOneByParams(SysUser.class , params);
	}

	@Override
	public SysUser selectUserByEmail(String email) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("email",email);
		return queryOneByExample(example);
	}

	/**
	 * 查询用户所属角色组
	 *
	 * @param userId 用户ID
	 * @return 结果
	 */
	@Override
	public String selectUserRoleGroup(Long userId)
	{
		List<SysRole> list = roleMapper.selectRolesByUserId(userId);
		StringBuffer idsStr = new StringBuffer();
		for (SysRole role : list)
		{
			idsStr.append(role.getRoleName()).append(",");
		}
		if (BlankUtil.isNotBlank(idsStr.toString()))
		{
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}

	/**
	 * 查询用户所属岗位组
	 *
	 * @param userId 用户ID
	 * @return 结果
	 */
	@Override
	public String selectUserPostGroup(Long userId)
	{
		List<SysPost> list = postMapper.selectPostsByUserId(userId);
		StringBuffer idsStr = new StringBuffer();
		for (SysPost post : list)
		{
			idsStr.append(post.getPostName()).append(",");
		}
		if (BlankUtil.isNotBlank(idsStr.toString()))
		{
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}

	/**
	 * 根据条件分页查询已分配用户角色列表
	 * @param user 用户信息
	 * @return 用户信息集合信息
	 */
	@DataScope(deptAlias = "d", userAlias = "u")
	@Override
	public List<SysUser> selectAllocatedList(SysUser user)
	{
		return userMapper.selectAllocatedList(user);
	}

	/**
	 * 根据条件分页查询未分配用户角色列表
	 *
	 * @param user 用户信息
	 * @return 用户信息集合信息
	 */
	@DataScope(deptAlias = "d", userAlias = "u")
	@Override
	public List<SysUser> selectUnallocatedList(SysUser user)
	{
		return userMapper.selectUnallocatedList(user);
	}



	@Override
	public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName)
	{
		if (BlankUtil.isBlank(userList))
		{
			throw new BusinessException("导入用户数据不能为空！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		String password = configService.selectConfigByKey("sys.user.initPassword");
		for (SysUser user : userList)
		{
			try
			{
				// 验证是否存在这个用户
				SysUser u = selectUserByLoginName(user.getLoginName());
				if (BlankUtil.isBlank(u))
				{
					user.setPassword(Md5Utils.hash(user.getLoginName() + password));
					user.setCreateBy(operName);
					this.saveNotNull(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 导入成功");
				}
				else if (isUpdateSupport)
				{
					user.setUpdateBy(operName);
					this.updateByIDNotNull(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 更新成功");
				}
				else
				{
					failureNum++;
					failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginName() + " 已存在");
				}
			}
			catch (Exception e)
			{
				failureNum++;
				String msg = "<br/>" + failureNum + "、账号 " + user.getLoginName() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
				log.error(msg, e);
			}
		}
		if (failureNum > 0)
		{
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new BusinessException(failureMsg.toString());
		}
		else
		{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}


	@Override
	public String checkLoginNameUnique(String loginName)
	{
		if (BlankUtil.isNotBlank(this.selectUserByLoginName(loginName)))
		{
			return UserConstants.USER_NAME_NOT_UNIQUE;
		}
		return UserConstants.USER_NAME_UNIQUE;
	}


	@Override
	public String checkPhoneUnique(SysUser user)
	{

		if (BlankUtil.isNotBlank(this.selectUserByPhoneNumber(user.getPhonenumber())))
		{
			return UserConstants.USER_PHONE_NOT_UNIQUE;
		}
		return UserConstants.USER_PHONE_UNIQUE;
	}


	@Override
	public String checkEmailUnique(SysUser user)
	{

		if (BlankUtil.isNotBlank(this.selectUserByEmail(user.getEmail())))
		{
			return UserConstants.USER_EMAIL_NOT_UNIQUE;
		}
		return UserConstants.USER_EMAIL_UNIQUE;
	}

}