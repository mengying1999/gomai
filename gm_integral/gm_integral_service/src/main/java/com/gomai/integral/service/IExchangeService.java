package com.gomai.integral.service;




import com.gomai.integral.vo.IChangeVo;
import com.gomai.intergral.pojo.IntegralExchange;
import com.gomai.intergral.pojo.IntegralGoods;

import java.util.List;

public interface IExchangeService {

    public IntegralGoods selectIGoodsByigId(int igId);

    public List<IChangeVo> selectIChangeVoByTypes(Integer uid, List<Integer> types);

    public int deleteByieId(int ieId);

    public IntegralExchange selectByieId(Integer ieId);

    public int insertIE(Integer uId, Integer igId,Integer num);

    public int deleteByuId(Integer uId);
}
