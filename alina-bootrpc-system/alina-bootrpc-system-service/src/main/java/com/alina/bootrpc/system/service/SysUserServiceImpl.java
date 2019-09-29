package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/9 21:55
 * @description：后端用户业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysUserService;
import com.alina.bootrpc.system.mapper.SysUserMapper;
import com.alina.bootrpc.system.model.SysUser;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service(value = SysUserServiceImpl.BEAN_NAME)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	public final static String BEAN_NAME = "userService";

	@Override
	public SysUser selectUserByLoginName(String userName) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("user_name",userName);
		return queryOneByExample(example);
	}

	@Override
	public SysUser selectUserByPhoneNumber(String phonenumber) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("phonenumber",phonenumber);
		return queryOneByExample(example);
	}

	@Override
	public SysUser selectUserByEmail(String email) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("email",email);
		return queryOneByExample(example);
	}
}