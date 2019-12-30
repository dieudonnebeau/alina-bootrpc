package com.alina.bootrpc.system.consumer.monitor.job;

import com.alina.bootrpc.common.core.annotation.Log;
import com.alina.bootrpc.common.core.domain.AjaxResult;
import com.alina.bootrpc.common.core.enums.BusinessType;
import com.alina.bootrpc.common.core.page.TableDataInfo;
import com.alina.bootrpc.common.core.utils.RequestBeanUtil;
import com.alina.bootrpc.common.core.utils.poi.ExcelUtil;
import com.alina.bootrpc.common.mapper.util.PageUtil;
import com.alina.bootrpc.quartz.facade.ISysJobLogService;
import com.alina.bootrpc.quartz.model.SysJob;
import com.alina.bootrpc.quartz.model.SysJobLog;
import com.alina.bootrpc.system.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController
{
    private String prefix = "monitor/job";

    @Autowired
    private ISysJobLogService jobLogService;

    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String jobLog()
    {
        return prefix + "/jobLog";
    }

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HttpServletRequest request)
    {
        RequestBeanUtil requestBeanUtil = new RequestBeanUtil(request);
        PageUtil<SysJobLog> pageInit = new PageUtil<>(pageNumber() , pageSize() , orderBy());
        PageUtil<SysJobLog> pageUtil = jobLogService.queryPage(pageInit, requestBeanUtil, SysJobLog.class);
        return getDataTable(pageUtil);
    }

    @Log(title = "调度日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HttpServletRequest request)
    {
        RequestBeanUtil requestBeanUtil = new RequestBeanUtil(request);;
        List<SysJobLog> list = jobLogService.queryList(requestBeanUtil,SysJobLog.class);
        ExcelUtil<SysJobLog> util = new ExcelUtil<>(SysJobLog.class);
        return util.exportExcel(list, "Jobs-Log-Datas");
    }

    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jobLogService.deleteByIDS(ids));
    }

    @RequiresPermissions("monitor:job:detail")
    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap)
    {
        mmap.put("name", "jobLog");
        mmap.put("jobLog", jobLogService.queryByID(jobLogId));
        return prefix + "/detail";
    }

    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}
