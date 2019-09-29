package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:45
 * @description：菜单权限业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysMenuService;
import com.alina.bootrpc.system.mapper.SysMenuMapper;
import com.alina.bootrpc.system.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = SysMenuServiceImpl.BEAN_NAME)
public class SysMenuServiceImpl  extends BaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    public final static String BEAN_NAME = "menuService";

    @Autowired
    private SysMenuMapper menuMapper;

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
}
