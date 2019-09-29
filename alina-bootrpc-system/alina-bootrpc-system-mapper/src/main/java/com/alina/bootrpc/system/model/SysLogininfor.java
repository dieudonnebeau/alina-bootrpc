package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:02
 * @description：系统访问记录实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysLogininfor implements Serializable {
    private static final long serialVersionUID = -8863821193273176908L;
    @Id
    private Long id;

    private String loginName;

    private String ipaddr;

    private String loginLocation;

    private String browser;

    private String os;

    private String status;

    private String msg;

    private Date loginTime;
}