package com.alina.bootrpc.common.core.enums;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:46
 * @description：业务操作类型
 * @modified By：
 * @version:     1.0
 */
public enum BusinessType
{
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,
    
    /**
     * 清空
     */
    CLEAN,
}
