package com.alina.bootrpc.system.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.system.model.SysDictType;

public interface SysDictTypeMapper  extends BaseMapper<SysDictType> {

    /**
     * 校验字典类型称是否唯一
     * @param dictType 字典类型
     * @return 结果
     */
    SysDictType checkDictTypeUnique(String dictType);

}