package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:12
 * @description：字典类型业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.constant.UserConstants;
import com.alina.bootrpc.common.core.domain.Ztree;
import com.alina.bootrpc.common.core.utils.StringUtils;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysDictTypeService;
import com.alina.bootrpc.system.mapper.SysDictTypeMapper;
import com.alina.bootrpc.system.model.SysDictType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(version="1.0.0")
public class SysDictTypeServiceImpl  extends BaseServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService {

    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Override
    public String checkDictTypeUnique(SysDictType dict)
    {
        Long id = StringUtils.isNull(dict.getId()) ? -1L : dict.getId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getId().longValue() != id.longValue())
        {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }

    /**
     * 查询字典类型树
     *
     * @param dictType 字典类型
     * @return 所有字典类型
     */
    @Override
    public List<Ztree> selectDictTree(SysDictType dictType)
    {
        List<Ztree> ztrees = new ArrayList<>();
        List<SysDictType> dictList = queryList(dictType);
        for (SysDictType dict : dictList)
        {
            if (UserConstants.DICT_NORMAL.equals(dict.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(dict.getId());
                ztree.setName(transDictName(dict));
                ztree.setTitle(dict.getDictType());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    public String transDictName(SysDictType dictType)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("(" + dictType.getDictName() + ")");
        sb.append("&nbsp;&nbsp;&nbsp;" + dictType.getDictType());
        return sb.toString();
    }

}
