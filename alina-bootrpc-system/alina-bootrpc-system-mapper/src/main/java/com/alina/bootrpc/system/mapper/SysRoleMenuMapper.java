package com.alina.bootrpc.system.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.system.model.SysRoleMenu;

import java.util.List;


public interface SysRoleMenuMapper  extends BaseMapper<SysRoleMenu> {

    /**
     * 通过角色ID删除角色和菜单关联
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleMenuByRoleId(Long roleId);

    /**
     * 批量删除角色菜单关联信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteRoleMenu(Long[] ids);

    /**
     * 查询菜单使用数量
     * @param menuId 菜单ID
     * @return 结果
     */
    int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 批量新增角色菜单信息
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}