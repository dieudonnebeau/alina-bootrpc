package com.alina.bootrpc.quartz.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 15:55
 * @description：定时任务调度日志实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysJobLog implements Serializable {
    private static final long serialVersionUID = -2450491466652935601L;
    @Id
    private Long id;

    private String jobName;

    private String jobGroup;

    private String invokeTarget;

    private String jobMessage;

    private String status;

    private String exceptionInfo;

    private Date createTime;

}