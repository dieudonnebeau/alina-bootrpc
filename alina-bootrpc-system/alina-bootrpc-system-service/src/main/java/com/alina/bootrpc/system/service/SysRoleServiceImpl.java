package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:36
 * @description：角色信息业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.constant.UserConstants;
import com.alina.bootrpc.common.core.text.Convert;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysRoleService;
import com.alina.bootrpc.system.mapper.SysRoleDeptMapper;
import com.alina.bootrpc.system.mapper.SysRoleMapper;
import com.alina.bootrpc.system.mapper.SysRoleMenuMapper;
import com.alina.bootrpc.system.mapper.SysUserRoleMapper;
import com.alina.bootrpc.system.model.SysRole;
import com.alina.bootrpc.system.model.SysRoleDept;
import com.alina.bootrpc.system.model.SysRoleMenu;
import com.alina.bootrpc.system.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(version="1.0.0")
public class SysRoleServiceImpl  extends BaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public Set<String> selectRoleKeys(Long userId) {
        SysRole sysRole = new SysRole();

        List<SysRole> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (BlankUtil.isNotBlank(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole role)
    {
        Long roleId = BlankUtil.isBlank(role.getId()) ? -1L : role.getId();
        Map<String , Object> param = new HashMap<>(16);
        param.put("roleName" , role.getRoleName());
        SysRole info = queryOneByParams(SysRole.class , param);
        if (BlankUtil.isNotBlank(info) && info.getId().longValue() != roleId.longValue())
        {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole role)
    {
        Long roleId = BlankUtil.isBlank(role.getId()) ? -1L : role.getId();
        Map<String , Object> param = new HashMap<>(16);
        param.put("roleKey" , role.getRoleKey());
        SysRole info = queryOneByParams(SysRole.class , param);
        if (BlankUtil.isNotBlank(info) && info.getId().longValue() != roleId.longValue())
        {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int authDataScope(SysRole role)
    {
        // 修改角色信息
        updateByIDNotNull(role);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(role.getId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
    }

    /**
     * 新增角色菜单信息
     * @param role 角色对象
     */
    public int insertRoleMenu(SysRole role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : role.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(SysRole role)
    {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDept> list = new ArrayList<SysRoleDept>();
        for (Long deptId : role.getDeptIds())
        {
            SysRoleDept rd = new SysRoleDept();
            rd.setRoleId(role.getId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0)
        {
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    @Override
    public int deleteAuthUser(SysUserRole userRole)
    {
        return userRoleMapper.deleteUserRoleInfo(userRole);
    }


    @Override
    public int deleteAuthUsers(Long roleId, String userIds)
    {
        return userRoleMapper.deleteUserRoleInfos(roleId, Convert.toLongArray(userIds));
    }

    @Override
    public int insertAuthUsers(Long roleId, String userIds)
    {
        Long[] users = Convert.toLongArray(userIds);
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (Long userId : users)
        {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return userRoleMapper.batchUserRole(list);
    }

}
