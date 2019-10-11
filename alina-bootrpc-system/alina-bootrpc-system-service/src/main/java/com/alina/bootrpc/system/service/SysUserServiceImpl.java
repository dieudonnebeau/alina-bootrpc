package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/9 21:55
 * @description：后端用户业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysUserService;
import com.alina.bootrpc.system.mapper.SysUserMapper;
import com.alina.bootrpc.system.model.SysUser;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;

@Service(version="1.0.0")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

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
}