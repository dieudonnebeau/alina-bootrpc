package com.alina.bootrpc.system.consumer.system;

import java.util.List;

import com.alina.bootrpc.common.core.annotation.Log;
import com.alina.bootrpc.common.core.controller.BaseController;
import com.alina.bootrpc.common.core.domain.AjaxResult;
import com.alina.bootrpc.common.core.enums.BusinessType;
import com.alina.bootrpc.common.core.page.TableDataInfo;
import com.alina.bootrpc.system.facade.ISysNoticeService;
import com.alina.bootrpc.system.framework.util.ShiroUtils;
import com.alina.bootrpc.system.model.SysNotice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/23 15:24
 * @description：公告 信息操作处理
 * @modified By：
 * @version:     1.0
 */
@Controller
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    private String prefix = "system/notice";

    @Autowired
    private ISysNoticeService noticeService;

    @RequiresPermissions("system:notice:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/notice";
    }

    /**
     * 查询公告列表
     */
    @RequiresPermissions("system:notice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.queryList(notice);
        return getDataTable(list);
    }

    /**
     * 新增公告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告
     */
    @RequiresPermissions("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysNotice notice)
    {
        notice.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(noticeService.saveNotNull(notice));
    }

    /**
     * 修改公告
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("notice", noticeService.queryByID(id));
        return prefix + "/edit";
    }

    /**
     * 修改保存公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysNotice notice)
    {
        notice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(noticeService.updateByIDNotNull(notice));
    }

    /**
     * 删除公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(noticeService.deleteByIDS(ids));
    }
}
