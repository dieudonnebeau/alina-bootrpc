package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 11:43
 * @description：参数配置实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1089694847886173917L;

    @Id
    private Integer id;

    private String configName;

    private String configKey;

    private String configValue;

    private String configType;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
}