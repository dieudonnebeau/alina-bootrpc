package com.alina.bootrpc.quartz.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 15:22
 * @description：定时任务调度主键实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysJobKey implements Serializable {
    private static final long serialVersionUID = 5702231191536718002L;
    @Id
    private Long id;

    private String jobName;

    private String jobGroup;
}