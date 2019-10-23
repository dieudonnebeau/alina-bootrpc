package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.core.domain.Ztree;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysMenu;
import com.alina.bootrpc.system.model.SysRole;
import com.alina.bootrpc.system.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysMenuService extends IBaseService<SysMenu> {

    /**
     * 根据用户ID查询权限
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据用户查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUser(SysUser user);

    /**
     * 查询菜单集合
     * @return 所有菜单信息
     */
    List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    /**
     * 查询子菜单数量
     * @param parentId 父级菜单ID
     * @return 结果
     */
    int selectCountMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     * @param menuId 菜单ID
     * @return 结果
     */
    int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 校验菜单名称是否唯一
     * @param menu 菜单信息
     * @return 结果
     */
    String checkMenuNameUnique(SysMenu menu);

    /**
     * 根据角色ID查询菜单
     * @param role 角色对象
     * @return 菜单列表
     */
    List<Ztree> roleMenuTreeData(SysRole role, Long userId);

    /**
     * 查询所有菜单
     * @return 菜单列表
     */
    List<Ztree> menuTreeData(Long userId);

    /**
     * 查询菜单集合
     * @return 所有菜单信息
     */
    List<SysMenu> selectMenuAll(Long userId);
}
