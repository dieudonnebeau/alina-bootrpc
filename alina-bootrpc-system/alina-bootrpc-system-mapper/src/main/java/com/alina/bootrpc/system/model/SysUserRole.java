package com.alina.bootrpc.system.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 16:39
 * @description：用户和角色关联实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 4875532771136642296L;
    @Id
    private Long id;

    private Long userId;

    private Long roleId;

}