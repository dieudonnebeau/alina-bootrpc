package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.core.domain.Ztree;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysDictType;

import java.util.List;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysDictTypeService extends IBaseService<SysDictType> {
    /**
     * 校验字典类型称是否唯一
     * @param dict 字典类型
     * @return 结果
     */
    String checkDictTypeUnique(SysDictType dict);

    /**
     * 查询字典类型树
     * @param dictType 字典类型
     * @return 所有字典类型
     */
    List<Ztree> selectDictTree(SysDictType dictType);
}
