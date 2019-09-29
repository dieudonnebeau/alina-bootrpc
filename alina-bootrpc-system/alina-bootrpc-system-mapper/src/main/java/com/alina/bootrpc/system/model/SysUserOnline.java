package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:36
 * @description：在线用户记录实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysUserOnline implements Serializable {
    private static final long serialVersionUID = -679874413418347493L;
    @Id
    private Long id;

    private String sessionid;

    private String loginName;

    private String deptName;

    private String ipaddr;

    private String loginLocation;

    private String browser;

    private String os;

    private String status;

    private Date startTimestamp;

    private Date lastAccessTime;

    private Integer expireTime;
}