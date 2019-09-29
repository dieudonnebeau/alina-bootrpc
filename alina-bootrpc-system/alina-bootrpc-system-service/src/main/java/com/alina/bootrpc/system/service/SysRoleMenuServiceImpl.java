package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:37
 * @description：角色和菜单关联业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysRoleMenuService;
import com.alina.bootrpc.system.mapper.SysRoleMenuMapper;
import com.alina.bootrpc.system.model.SysRoleMenu;
import org.springframework.stereotype.Service;

/**
 * Created by 1 on 2019/9/16.
 */
@Service(value = SysRoleMenuServiceImpl.BEAN_NAME)
public class SysRoleMenuServiceImpl  extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
    public final static String BEAN_NAME = "roleMenuService";
}
