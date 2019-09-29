package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:10
 * @description：操作日志记录实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysOperLog implements Serializable {
    private static final long serialVersionUID = 1582440936435469443L;
    @Id
    private Long id;

    private String title;

    private Integer businessType;

    private String method;

    private String requestMethod;

    private Integer operatorType;

    private String operName;

    private String deptName;

    private String operUrl;

    private String operIp;

    private String operLocation;

    private String operParam;

    private Integer status;

    private String errorMsg;

    private Date operTime;

}