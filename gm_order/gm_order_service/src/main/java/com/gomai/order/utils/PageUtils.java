package com.gomai.order.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gomai.order.vo.QueryParams;
import com.gomai.utils.PageResult;
import org.springframework.util.StringUtils;
import java.util.List;

public class PageUtils {

    /**
     * 设置请求分页数据
     */
    public void startPage(QueryParams queryParams)
    {
        Integer pageNum = queryParams.getPage();
        Integer pageSize = queryParams.getRows();
        if (!StringUtils.isEmpty(queryParams.getPage()) && !StringUtils.isEmpty(queryParams.getRows()))
        {
            PageHelper.startPage(queryParams.getPage(), queryParams.getRows());
        }
    }

    /**
     * 响应请求分页数据
     */
    public PageResult getDataTable(List<?> list)
    {
        PageResult pageResult = new PageResult();
        pageResult.setRows(list);
        pageResult.setTotal(new PageInfo(list).getTotal());
        return pageResult;
    }
}
