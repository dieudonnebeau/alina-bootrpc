package com.alina.bootrpc.quartz.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 13:59
 * @description：定时任务调度
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysJob extends SysJobKey implements Serializable {

    private static final long serialVersionUID = 1691530854274890540L;
    private String invokeTarget;

    private String cronExpression;

    private String misfirePolicy;

    private String concurrent;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
}