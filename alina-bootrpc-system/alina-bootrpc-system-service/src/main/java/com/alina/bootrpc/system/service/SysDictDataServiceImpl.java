package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:13
 * @description：字典数据业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysDictDataService;
import com.alina.bootrpc.system.mapper.SysDictDataMapper;
import com.alina.bootrpc.system.model.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version="1.0.0")
public class SysDictDataServiceImpl  extends BaseServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType , dictValue);
    }
}
