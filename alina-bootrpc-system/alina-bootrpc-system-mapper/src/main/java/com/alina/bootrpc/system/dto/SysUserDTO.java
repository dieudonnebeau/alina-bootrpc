package com.alina.bootrpc.system.dto;

import com.alina.bootrpc.system.model.SysDept;
import com.alina.bootrpc.system.model.SysRole;
import com.alina.bootrpc.system.model.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/30 11:05
 * @description：系统用户参数对象
 * @modified By：
 * @version:     1.0
 */
@Data
public class SysUserDTO extends SysUser implements Serializable {
    private static final long serialVersionUID = -5259614731516384120L;
    /**
     * 部门信息
     */
    private SysDept dept;
    /**
     * 权限列表
     */
    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;
}
