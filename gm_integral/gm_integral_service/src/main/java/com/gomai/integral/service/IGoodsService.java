package com.gomai.integral.service;



import com.gomai.intergral.pojo.IntegralGoods;

import java.util.List;

public interface IGoodsService {

    public IntegralGoods SelectByigId(int igId);


    List<IntegralGoods> selecetByigType(String igType);
}
