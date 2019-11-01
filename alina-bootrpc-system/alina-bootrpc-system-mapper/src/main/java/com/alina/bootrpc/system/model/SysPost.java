package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:15
 * @description：岗位信息实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysPost implements Serializable {
    private static final long serialVersionUID = 4211304956480457659L;
    @Id
    private Long id;

    private String postCode;

    private String postName;

    private Integer postSort;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    @Transient
    private Boolean flag;
}