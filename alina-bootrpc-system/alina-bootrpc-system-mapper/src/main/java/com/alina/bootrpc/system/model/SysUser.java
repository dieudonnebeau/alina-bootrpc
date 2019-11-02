package com.alina.bootrpc.system.model;

import com.alina.bootrpc.common.core.annotation.Excel;
import lombok.Data;

import javax.persistence.*;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Excel(name = "编号")
    private Long id;

    @Excel(name = "部门编号")
    private Long deptId;

    @Excel(name = "登录名")
    private String loginName;

    @Excel(name = "真实名称")
    private String userName;

    @Excel(name = "类型")
    private String userType;

    @Excel(name = "邮箱")
    private String email;

    @Excel(name = "手机号码")
    private String phonenumber;

    @Excel(name = "性别", readConverterExp = ("0=男,1=女,2=未知"))
    private String sex;

    private String avatar;

    private String password;

    private String salt;

    @Excel(name = "状态", readConverterExp = ("0=正常,1=停用"))
    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private String deptName;

    public boolean isAdmin()
    {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long id)
    {
        return id != null && 1L == id;
    }
}