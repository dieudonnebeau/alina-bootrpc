package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:36
 * @description：角色信息业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysRoleService;
import com.alina.bootrpc.system.mapper.SysRoleMapper;
import com.alina.bootrpc.system.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 2019/9/16.
 */
@Service(value = SysRoleServiceImpl.BEAN_NAME)
public class SysRoleServiceImpl  extends BaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    public final static String BEAN_NAME = "roleService";

    @Autowired
    private SysRoleMapper roleMapper;

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
}
