package com.alina.bootrpc.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Map;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:48
 * @description：Entity基类
 * @modified By：
 * @version:     1.0
 */
@Data
public class BaseEntity implements Serializable
{

    private static final long serialVersionUID = 5250571875355899041L;

    @Id
    private Long id;

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;

}
