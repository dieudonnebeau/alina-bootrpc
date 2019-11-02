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
import com.alina.bootrpc.common.core.text.Convert;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.core.utils.security.Md5Utils;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.dto.SysUserDTO;
import com.alina.bootrpc.system.facade.ISysConfigService;
import com.alina.bootrpc.system.facade.ISysDeptService;
import com.alina.bootrpc.system.facade.ISysUserService;
import com.alina.bootrpc.system.mapper.*;
import com.alina.bootrpc.system.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
	private SysUserRoleMapper userRoleMapper;

	@Autowired
	private SysUserPostMapper userPostMapper;

	@Autowired
	private ISysConfigService configService;

	@Autowired
    private ISysDeptService deptService;

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


	@Override
	@Transactional
	public Integer insertUser(SysUserDTO userDTO)
	{
		// 新增用户信息
		SysUser user = new SysUser();
		BeanUtils.copyProperties(userDTO,user);
		SysDept dept = deptService.queryByID(userDTO.getDeptId());
		if(BlankUtil.isNotBlank(dept)){
		    user.setDeptName(dept.getDeptName());
        }
		int rows = saveNotNull(user);

		// 新增用户岗位关联
		userDTO.setId(user.getId());
		insertUserPost(userDTO);
		// 新增用户与角色管理
		insertUserRole(userDTO);
		return rows;
	}

	/**
	 * 新增用户角色信息
	 *
	 * @param userDTO 用户对象
	 */
	public void insertUserRole(SysUserDTO userDTO)
	{
		Long[] roles = userDTO.getRoleIds();
		if (BlankUtil.isNotBlank(roles))
		{
			// 新增用户与角色管理
			List<SysUserRole> list = new ArrayList<>();
			for (Long roleId : roles)
			{
				SysUserRole ur = new SysUserRole();
				ur.setUserId(userDTO.getId());
				ur.setRoleId(roleId);
				list.add(ur);
			}
			if (list.size() > 0)
			{
				userRoleMapper.batchUserRole(list);
			}
		}
	}

	/**
	 * 新增用户岗位信息
	 *
	 * @param userDTO 用户对象
	 */
	public void insertUserPost(SysUserDTO userDTO)
	{
		Long[] posts = userDTO.getPostIds();
		if (BlankUtil.isNotBlank(posts))
		{
			// 新增用户与岗位管理
			List<SysUserPost> list = new ArrayList<>();
			for (Long postId : posts)
			{
				SysUserPost up = new SysUserPost();
				up.setUserId(userDTO.getId());
				up.setPostId(postId);
				list.add(up);
			}
			if (list.size() > 0)
			{
				userPostMapper.batchUserPost(list);
			}
		}
	}


	/**
	 * 修改保存用户信息
	 *
	 * @param userDTO 用户信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public Integer updateUser(SysUserDTO userDTO)
	{
		SysUser user = new SysUser();
		BeanUtils.copyProperties(userDTO,user);
		Long userId = userDTO.getId();
		// 删除用户与角色关联
		userRoleMapper.deleteUserRoleByUserId(userId);
		// 新增用户与角色管理
		insertUserRole(userDTO);
		// 删除用户与岗位关联
		userPostMapper.deleteUserPostByUserId(userId);
		// 新增用户与岗位管理
		insertUserPost(userDTO);
		SysDept dept = deptService.queryByID(userDTO.getDeptId());
		user.setDeptName(dept.getDeptName());
		return updateByIDNotNull(user);
	}


	/**
	 * 批量删除用户信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public Integer deleteUserByIds(String ids) throws BusinessException
	{
		Long[] userIds = Convert.toLongArray(ids);
		for (Long userId : userIds)
		{
			if (SysUser.isAdmin(userId))
			{
				throw new BusinessException("不允许删除超级管理员用户");
			}
			// 删除用户与角色关联
			userRoleMapper.deleteUserRoleByUserId(userId);
			// 删除用户与岗位关联
			userPostMapper.deleteUserPostByUserId(userId);
		}
		return deleteByIDS(ids);
	}




}