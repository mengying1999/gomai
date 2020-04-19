package com.gomai.goods.service.impl;

import com.gomai.goods.mapper.Category2Mapper;
import com.gomai.goods.mapper.UnshelveMapper;
import com.gomai.goods.pojo.Unshelve;
import com.gomai.goods.service.UnshelveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UnshelveServiceImpl implements UnshelveService {
    @Autowired
    private UnshelveMapper unshelveMapper;

    @Override
    public int addUnshelve(Unshelve unshelve) {//添加下架商品
        int flag = this.unshelveMapper.insert(unshelve);
        return flag;
    }
    @Override
    public int deleteUnshelve(Integer g_id){//删除下架记录
        Example example = new Example(Unshelve.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("gId", g_id);
        int flag = this.unshelveMapper.deleteByExample(example);
        return flag;
    }
    @Override
    public List<Unshelve> queryUnshelveGoodsByGId(Integer g_id){////根据gid查询下架记录
        Example example = new Example(Unshelve.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("gId", g_id);

        List<Unshelve> unshelveGood = this.unshelveMapper.selectByExample(example);
        return unshelveGood;
    }
}
