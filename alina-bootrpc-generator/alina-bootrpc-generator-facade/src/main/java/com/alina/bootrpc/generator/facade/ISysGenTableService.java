package com.alina.bootrpc.generator.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.generator.model.SysGenTable;

import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysGenTableService extends IBaseService<SysGenTable> {

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<SysGenTable> selectDbTableList(SysGenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<SysGenTable> selectDbTableListByNames(String[] tableNames);


    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     * @param operName 操作人员
     */
    void importGenTable(List<SysGenTable> tableList, String operName);

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    Map<String, String> previewCode(Long tableId);

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] generatorCode(String[] tableNames);

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    void validateEdit(SysGenTable genTable);
}
