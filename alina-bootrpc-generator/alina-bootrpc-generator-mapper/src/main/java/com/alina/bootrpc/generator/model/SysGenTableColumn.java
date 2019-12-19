package com.alina.bootrpc.generator.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 13:55
 * @description：代码生成业务实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysGenTableColumn implements Serializable {
    private static final long serialVersionUID = -5910513158457797833L;
    @Id
    private Long id;

    private String tableId;

    private String columnName;

    private String columnComment;

    private String columnType;

    private String javaType;

    private String javaField;

    private String isPk;

    private String isIncrement;

    private String isRequired;

    private String isInsert;

    private String isEdit;

    private String isList;

    private String isQuery;

    private String queryType;

    private String htmlType;

    private String dictType;

    private Integer sort;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}