package com.alina.bootrpc.system.service;

/**
 * @author     ：迪艾多(Dieudonne)
 * @date       ：Created on 2019/9/16 16:10
 * @description：用户与角色关联业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.mapper.SysUserRoleMapper;
import com.alina.bootrpc.system.model.SysUserRole;
import com.alibaba.dubbo.config.annotation.Service;
@Service(version="1.0.0")
public class SysUserRoleServiceImpl  extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements IBaseService<SysUserRole> {
}
