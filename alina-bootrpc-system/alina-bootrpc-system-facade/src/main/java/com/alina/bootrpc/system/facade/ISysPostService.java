package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysPost;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysPostService extends IBaseService<SysPost> {



    /**
     * 校验岗位名称是否唯一
     * @param post 岗位信息
     * @return 结果
     */
    String checkPostNameUnique(SysPost post);

    /**
     * 校验岗位编码是否唯一
     * @param post 岗位信息
     * @return 结果
     */
    String checkPostCodeUnique(SysPost post);
}
