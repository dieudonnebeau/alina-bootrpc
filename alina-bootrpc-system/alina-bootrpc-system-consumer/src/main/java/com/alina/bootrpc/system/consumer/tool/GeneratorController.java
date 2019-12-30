package com.alina.bootrpc.system.consumer.tool;

import com.alina.bootrpc.common.core.annotation.Log;
import com.alina.bootrpc.common.core.domain.AjaxResult;
import com.alina.bootrpc.common.core.enums.BusinessType;
import com.alina.bootrpc.common.core.page.TableDataInfo;
import com.alina.bootrpc.common.core.text.Convert;
import com.alina.bootrpc.common.core.utils.RequestBeanUtil;
import com.alina.bootrpc.common.mapper.util.PageUtil;
import com.alina.bootrpc.generator.facade.ISysGenTableColumnService;
import com.alina.bootrpc.generator.facade.ISysGenTableService;
import com.alina.bootrpc.generator.model.SysGenTable;
import com.alina.bootrpc.generator.model.SysGenTableColumn;
import com.alina.bootrpc.system.base.BaseController;
import com.alina.bootrpc.system.framework.util.PermissionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 */
@Controller
@RequestMapping("/tool/gen")
public class GeneratorController extends BaseController
{
    private String prefix = "tool/gen";

    @Autowired
    private ISysGenTableService genTableService;

    @Autowired
    private ISysGenTableColumnService genTableColumnService;

    @RequiresPermissions("tool:gen:view")
    @GetMapping()
    public String gen()
    {
        return prefix + "/gen";
    }

    /**
     * 查询代码生成列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo genList(HttpServletRequest request)
    {
        RequestBeanUtil requestBeanUtil = new RequestBeanUtil(request);
        PageUtil<SysGenTable> pageInit = new PageUtil<>(pageNumber() , pageSize() , orderBy());
        PageUtil<SysGenTable> pageUtil = genTableService.queryPage(pageInit, requestBeanUtil, SysGenTable.class);
        return getDataTable(pageUtil);
    }

    /**
     * 查询数据库列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/db/list")
    @ResponseBody
    public TableDataInfo dataList(SysGenTable genTable)
    {

        startPage();
        List<SysGenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/column/list")
    @ResponseBody
    public TableDataInfo columnList(HttpServletRequest request)
    {
        TableDataInfo dataInfo = new TableDataInfo();
        RequestBeanUtil requestBeanUtil = new RequestBeanUtil(request);
        List<SysGenTableColumn> list = genTableColumnService.queryList(requestBeanUtil , SysGenTableColumn.class);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构
     */
    @RequiresPermissions("tool:gen:list")
    @GetMapping("/importTable")
    public String importTable()
    {
        return prefix + "/importTable";
    }

    /**
     * 导入表结构（保存）
     */
    @RequiresPermissions("tool:gen:list")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    @ResponseBody
    public AjaxResult importTableSave(String tables)
    {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<SysGenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        String operName = (String) PermissionUtils.getPrincipalProperty("loginName");
        genTableService.importGenTable(tableList, operName);
        return AjaxResult.success();
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping("/edit/{tableId}")
    public String edit(@PathVariable("tableId") Long tableId, ModelMap mmap)
    {
        SysGenTable table = genTableService.queryByID(tableId);
        mmap.put("table", table);
        return prefix + "/edit";
    }

    /**
     * 修改保存代码生成业务
     */
    @RequiresPermissions("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysGenTable genTable)
    {
        genTableService.validateEdit(genTable);
        genTableService.updateByID(genTable);
        return AjaxResult.success();
    }

    @RequiresPermissions("tool:gen:remove")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        genTableService.deleteByIDS(ids);
        return AjaxResult.success();
    }

    /**
     * 预览代码
     */
    @RequiresPermissions("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    @ResponseBody
    public AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException
    {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return AjaxResult.success(dataMap);
    }

    /**
     * 生成代码
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        byte[] data = genTableService.generatorCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.generatorCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"alina.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}