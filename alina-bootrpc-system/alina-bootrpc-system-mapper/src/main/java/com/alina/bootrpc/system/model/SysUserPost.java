package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:38
 * @description：用户与岗位关联实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysUserPost implements Serializable {
    private static final long serialVersionUID = 8718439167145089353L;
    @Id
    private Long id;

    private Long userId;

    private Long postId;
}