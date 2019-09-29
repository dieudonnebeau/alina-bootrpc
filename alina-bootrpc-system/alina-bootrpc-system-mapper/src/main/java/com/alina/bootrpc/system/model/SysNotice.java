package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:08
 * @description：通知公告实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysNotice implements Serializable {
    private static final long serialVersionUID = 8440737166155472691L;
    @Id
    private Integer id;

    private String noticeTitle;

    private String noticeType;

    private String noticeContent;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}