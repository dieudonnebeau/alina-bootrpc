package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:07
 * @description：菜单权限实体表
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1903655888572520310L;
    @Id
    private Long id;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String url;

    private String target;

    private String menuType;

    private String visible;

    private String perms;

    private String icon;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
}