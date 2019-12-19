package com.alina.bootrpc.generator.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 13:51
 * @description：代码生成业务实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysGenTable implements Serializable {
    private static final long serialVersionUID = 2572729728828084712L;
    @Id
    private Long id;

    private String tableName;

    private String tableComment;

    private String className;

    private String tplCategory;

    private String packageName;

    private String moduleName;

    private String businessName;

    private String functionName;

    private String functionAuthor;

    private String options;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
}