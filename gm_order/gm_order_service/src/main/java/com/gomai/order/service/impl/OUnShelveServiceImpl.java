package com.gomai.order.service.impl;

import com.gomai.goods.pojo.Unshelve;
import com.gomai.order.mapper.OUnshelveMapper;
import com.gomai.order.service.OUnshelveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OUnShelveServiceImpl implements OUnshelveService {
    @Autowired
    private OUnshelveMapper oUnshelveMapper;
    @Override
    public int addUnshelve(Unshelve unshelve) {
        int flag = this.oUnshelveMapper.insert(unshelve);
        return flag;
    }
}
