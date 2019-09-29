package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysMenu;

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
}
