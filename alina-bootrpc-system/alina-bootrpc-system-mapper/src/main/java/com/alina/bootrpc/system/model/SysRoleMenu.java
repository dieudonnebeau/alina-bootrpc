package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:29
 * @description：角色和菜单关联实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 6551656278310642942L;
    @Id
    private Long id;

    private Long roleId;

    private Long menuId;
}