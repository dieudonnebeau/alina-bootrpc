package com.alina.bootrpc.common.core.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyResponse implements Serializable {
    private Integer id;

    private String name;

    private Integer parentId;

    private String picPath;

    private String state;

    private String createTime;

    private String remark;

    private String storeId;

    private String attr3;

    private String level;
    
    private List<ClassifyResponse> children;
}