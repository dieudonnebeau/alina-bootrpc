package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 13:34
 * @description：字典类型实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysDictType implements Serializable {
    private static final long serialVersionUID = -473558361399214182L;
    @Id
    private Long id;

    private String dictName;

    private String dictType;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
}