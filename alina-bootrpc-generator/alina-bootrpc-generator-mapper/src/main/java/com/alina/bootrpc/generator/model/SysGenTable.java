package com.alina.bootrpc.generator.model;

import com.alina.bootrpc.common.core.constant.GenConstants;
import com.alina.bootrpc.common.core.utils.StringUtils;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/6 13:51
 * @description：代码生成业务实体类
 * @modified By：
 * @version: 1.0
 */
@Data
public class SysGenTable implements Serializable {
    private static final long serialVersionUID = 2572729728828084712L;
    /** 编号 */
    @Id
    private Long id;

    /** 表名称 */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /** 表描述 */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    /** 实体类名称(首字母大写) */
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    /** 使用的模板（crud单表操作 tree树表操作） */
    private String tplCategory;

    /** 生成包路径 */
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /** 生成模块名 */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /** 生成业务名 */
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    /** 生成功能名 */
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    /** 生成作者 */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /** 其它生成选项 */
    private String options;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;

    /** 主键信息 */
    private SysGenTableColumn pkColumn;

    /** 表列信息 */
    @Valid
    private List<SysGenTableColumn> columns;


    /** 树编码字段 */
    private String treeCode;

    /** 树父编码字段 */
    private String treeParentCode;

    /** 树名称字段 */
    private String treeName;


    public boolean isTree()
    {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud()
    {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField)
    {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField)
    {
        if (isTree(tplCategory))
        {
            StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.TREE_ENTITY);
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }
}