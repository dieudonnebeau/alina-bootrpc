package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:37
 * @description：角色和菜单关联业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.mapper.SysRoleMenuMapper;
import com.alina.bootrpc.system.model.SysRoleMenu;

@Service(version="1.0.0")
public class SysRoleMenuServiceImpl  extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements IBaseService<SysRoleMenu> {
}
