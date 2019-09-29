package com.alina.bootrpc.common.core.page;


import com.alina.bootrpc.common.core.constant.Constants;
import com.alina.bootrpc.common.core.utils.ServletUtils;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 9:43
 * @description：表格数据处理
 * @modified By：
 * @version:     1.0
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
