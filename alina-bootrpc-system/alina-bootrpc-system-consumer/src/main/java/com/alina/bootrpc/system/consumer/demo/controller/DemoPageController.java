package com.alina.bootrpc.system.consumer.demo.controller;

import com.alina.bootrpc.system.consumer.demo.domain.DemoDatas;
import com.alina.bootrpc.system.consumer.demo.domain.UserFormModel;
import com.alina.bootrpc.system.consumer.demo.domain.UserOperateModel;
import com.alina.bootrpc.system.consumer.demo.domain.UserTableModel;
import com.alina.bootrpc.system.base.BaseController;
import com.alina.bootrpc.common.core.domain.AjaxResult;
import com.alina.bootrpc.common.core.exception.BusinessException;
import com.alina.bootrpc.common.core.page.PageDomain;
import com.alina.bootrpc.common.core.page.TableDataInfo;
import com.alina.bootrpc.common.core.page.TableSupport;
import com.alina.bootrpc.common.core.text.Convert;
import com.alina.bootrpc.common.core.utils.StringUtils;
import com.alina.bootrpc.common.core.utils.poi.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/8 19:34
 * @description：静态资源跳转公用控制器
 * @modified By：
 * @version:     1.0
 */
@Controller
public class DemoPageController extends BaseController {
    private String prefix = "demo/modal";

    private DemoDatas demoDatas = new DemoDatas();

    /**
     * 获取用户数据
     */
    @GetMapping("/demo/form/userModel")
    @ResponseBody
    public AjaxResult userModel()
    {
        AjaxResult ajax = new AjaxResult();
        List<UserFormModel> users =  demoDatas.getUsers();

        ajax.put("code", 200);
        ajax.put("value", users);
        return ajax;
    }

    /**
     * 获取数据集合
     */
    @GetMapping("/demo/form/collection")
    @ResponseBody
    public AjaxResult collection()
    {
        String[] array = { "ruoyi 1", "ruoyi 2", "ruoyi 3", "ruoyi 4", "ruoyi 5" };
        AjaxResult ajax = new AjaxResult();
        ajax.put("value", array);
        return ajax;
    }


    /**
     * 查询数据
     */
    @PostMapping("/demo/operate/list")
    @ResponseBody
    public TableDataInfo list(UserOperateModel userModel)
    {
        TableDataInfo rspData = new TableDataInfo();
        // 查询条件过滤
        List<UserOperateModel> userList = new ArrayList<UserOperateModel>(demoDatas.getUsersMap().values());
        if (StringUtils.isNotEmpty(userModel.getSearchValue()))
        {
            userList.clear();
            for (Map.Entry<Integer, UserOperateModel> entry : demoDatas.getUsersMap().entrySet())
            {
                if (entry.getValue().getUserName().equals(userModel.getSearchValue()))
                {
                    userList.add(entry.getValue());
                }
            }
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize())
        {
            rspData.setRows(userList);
            rspData.setTotal(userList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > userList.size())
        {
            pageSize = userList.size();
        }
        rspData.setRows(userList.subList(pageNum, pageSize));
        rspData.setTotal(userList.size());
        return rspData;
    }

    /**
     * 新增保存用户
     */
    @PostMapping("/demo/operate/add")
    @ResponseBody
    public AjaxResult addSave(UserOperateModel user)
    {
        Integer userId = demoDatas.getUsers().size() + 1;
        user.setUserId(userId);
        return AjaxResult.success(demoDatas.getUsersMap().put(userId, user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/demo/operate/edit/{userId}")
    public String edit(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        mmap.put("user", demoDatas.getUsers().get(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @PostMapping("/demo/operate/edit")
    @ResponseBody
    public AjaxResult editSave(UserOperateModel user)
    {
        return AjaxResult.success(demoDatas.getUsersMap().put(user.getUserId(), user));
    }

    /**
     * 导出
     */
    @PostMapping("/demo/operate/export")
    @ResponseBody
    public AjaxResult export(UserOperateModel user)
    {
        List<UserOperateModel> list = new ArrayList<UserOperateModel>(demoDatas.getUsersMap().values());
        ExcelUtil<UserOperateModel> util = new ExcelUtil<UserOperateModel>(UserOperateModel.class);
        return util.exportExcel(list, "用户数据");
    }

    /**
     * 下载模板
     */
    @GetMapping("/demo/operate/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<UserOperateModel> util = new ExcelUtil<UserOperateModel>(UserOperateModel.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 导入数据
     */
    @PostMapping("/demo/operate/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<UserOperateModel> util = new ExcelUtil<UserOperateModel>(UserOperateModel.class);
        List<UserOperateModel> userList = util.importExcel(file.getInputStream());
        String message = importUser(userList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 删除用户
     */
    @PostMapping("/demo/operate/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Integer[] userIds = Convert.toIntArray(ids);
        for (Integer userId : userIds)
        {
            demoDatas.getUsers().remove(userId);
        }
        return AjaxResult.success();
    }

    /**
     * 查看详细
     */
    @GetMapping("/demo/operate/detail/{userId}")
    public String detail(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        mmap.put("user", demoDatas.getUsers().get(userId));
        return prefix + "/detail";
    }

    @PostMapping("/demo/operate/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        demoDatas.getUsers().clear();
        return success();
    }


    /**
     * 模态窗口
     */
    @GetMapping("/demo/{folder}/{page}")
    public String page(@PathVariable String folder , @PathVariable String page )
    {
        StringBuilder builder = new StringBuilder("/demo/").append(folder).append("/").append(page);
        return builder.toString();
    }


     /**
     * 查询数据
     */
    @PostMapping("/demo/table/list")
    @ResponseBody
    public TableDataInfo list(UserTableModel userModel)
    {
        TableDataInfo rspData = new TableDataInfo();
        List<UserTableModel> userList = new ArrayList<UserTableModel>(Arrays.asList(new UserTableModel[demoDatas.getTableUsers().size()]));
        Collections.copy(userList, demoDatas.getTableUsers());
        // 查询条件过滤
        if (StringUtils.isNotEmpty(userModel.getUserName()))
        {
            userList.clear();
            for (UserTableModel user : demoDatas.getTableUsers())
            {
                if (user.getUserName().equals(userModel.getUserName()))
                {
                    userList.add(user);
                }
            }
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize())
        {
            rspData.setRows(userList);
            rspData.setTotal(userList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > userList.size())
        {
            pageSize = userList.size();
        }
        rspData.setRows(userList.subList(pageNum, pageSize));
        rspData.setTotal(userList.size());
        return rspData;
    }

    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importUser(List<UserOperateModel> userList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (UserOperateModel user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                boolean userFlag = false;
                for (Map.Entry<Integer, UserOperateModel> entry : demoDatas.getUsersMap().entrySet())
                {
                    if (entry.getValue().getUserName().equals(user.getUserName()))
                    {
                        userFlag = true;
                        break;
                    }
                }
                if (!userFlag)
                {
                    Integer userId = demoDatas.getUsers().size() + 1;
                    user.setUserId(userId);
                    demoDatas.getUsersMap().put(userId, user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用户 " + user.getUserName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    demoDatas.getUsersMap().put(user.getUserId(), user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用户 " + user.getUserName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用户 " + user.getUserName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

}