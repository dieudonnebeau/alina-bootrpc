package com.alina.bootrpc.quartz.model;

import com.alina.bootrpc.common.core.annotation.Excel;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 15:55
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysJobLog implements Serializable {
    private static final long serialVersionUID = -2450491466652935601L;
    @Id
    /** ID */
    @Excel(name = "日志序号")
    private Long id;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String jobName;

    /** 任务组名 */
    @Excel(name = "任务组名")
    private String jobGroup;

    /** 调用目标字符串 */
    @Excel(name = "调用目标字符串")
    private String invokeTarget;

    /** 日志信息 */
    @Excel(name = "日志信息")
    private String jobMessage;

    /** 执行状态（0正常 1失败） */
    @Excel(name = "执行状态", readConverterExp = "0=正常,1=失败")
    private String status;

    /** 异常信息 */
    @Excel(name = "异常信息")
    private String exceptionInfo;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    private Date createTime;

}