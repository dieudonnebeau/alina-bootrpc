package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:45
 * @description：菜单权限业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.constant.UserConstants;
import com.alina.bootrpc.common.core.domain.Ztree;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysMenuService;
import com.alina.bootrpc.system.mapper.SysMenuMapper;
import com.alina.bootrpc.system.mapper.SysRoleMenuMapper;
import com.alina.bootrpc.system.model.SysMenu;
import com.alina.bootrpc.system.model.SysRole;
import com.alina.bootrpc.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service(version="1.0.0")
public class SysMenuServiceImpl  extends BaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    public final static String BEAN_NAME = "menuService";

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (BlankUtil.isNotBlank(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user)
    {
        List<SysMenu> menus = new LinkedList<SysMenu>();
        // 管理员显示所有菜单信息
        if (user.isAdmin())
        {
            menus = menuMapper.selectMenuNormalAll();
        }
        else
        {
            menus = menuMapper.selectMenusByUserId(user.getId());
        }
        return getChildPerms(menus, 0);
    }


    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId)
    {
        List<SysMenu> menuList = null;
        if (SysUser.isAdmin(userId))
        {
            menuList = menuMapper.selectMenuList(menu);
        }
        else
        {
            menu.getParams().put("userId", userId);
            menuList = menuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext())
                {
                    SysMenu n = (SysMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }


    private boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }


    @Override
    public int selectCountMenuByParentId(Long parentId)
    {
        return menuMapper.selectCountMenuByParentId(parentId);
    }

    @Override
    public int selectCountRoleMenuByMenuId(Long menuId)
    {
        return roleMenuMapper.selectCountRoleMenuByMenuId(menuId);
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu)
    {
        Long menuId = BlankUtil.isBlank(menu.getId()) ? -1L : menu.getId();
        SysMenu info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (BlankUtil.isNotBlank(info) && info.getId().longValue() != menuId.longValue())
        {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Ztree> roleMenuTreeData(SysRole role, Long userId)
    {
        Long roleId = role.getId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysMenu> menuList = selectMenuAll(userId);
        if (BlankUtil.isNotBlank(roleId))
        {
            List<String> roleMenuList = menuMapper.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Ztree> menuTreeData(Long userId)
    {
        List<SysMenu> menuList = selectMenuAll(userId);
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<SysMenu> selectMenuAll(Long userId)
    {
        List<SysMenu> menuList = null;
        if (SysUser.isAdmin(userId))
        {
            menuList = queryAll();
        }
        else
        {
            menuList = menuMapper.selectMenuAllByUserId(userId);
        }
        return menuList;
    }

    /**
     * 对象转菜单树
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenu> menuList)
    {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenu> menuList, List<String> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = BlankUtil.isNotBlank(roleMenuList);
        for (SysMenu menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck)
            {
                ztree.setChecked(roleMenuList.contains(menu.getId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(SysMenu menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }





}
