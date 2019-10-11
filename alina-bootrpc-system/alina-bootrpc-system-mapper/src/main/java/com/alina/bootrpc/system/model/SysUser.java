package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:31
 * @description：用户信息实体类
 * @modified By：
 * @version: 1.0
 */
@Data
@Table(name = "sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 3611104699562919482L;
    @Id
    private Long id;

    private Long deptId;

    private String loginName;

    private String userName;

    private String userType;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String password;

    private String salt;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    @Transient
    private SysDept dept;

    @Transient
    private List<SysRole> roles;

    public boolean isAdmin()
    {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long id)
    {
        return id != null && 1L == id;
    }
}