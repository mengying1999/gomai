package com.gomai.goods.service;

import com.gomai.goods.pojo.Unshelve;

import java.util.List;

public interface UnshelveService {
    public int addUnshelve(Unshelve unshelve);//添加下架记录
    public int deleteUnshelve(Integer g_id);//删除下架记录
    public List<Unshelve> queryUnshelveGoodsByGId(Integer g_id);//根据gid查询下架记录
}
