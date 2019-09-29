package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 13:29
 * @description：部门实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysDept implements Serializable {
    private static final long serialVersionUID = -1770650162319137330L;
    @Id
    private Long id;

    private Long parentId;

    private String ancestors;

    private String deptName;

    private Integer orderNum;

    private String leader;

    private String phone;

    private String email;

    private String status;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

}