package com.gomai.integral.service;



import com.gomai.intergral.pojo.IntegralGoods;

import java.util.List;

public interface IGoodsService {

    public IntegralGoods SelectByigId(int igId);


    public List<IntegralGoods> selecetByigType(String igType);

    public List<IntegralGoods> SelectByigName(String igName);
}
