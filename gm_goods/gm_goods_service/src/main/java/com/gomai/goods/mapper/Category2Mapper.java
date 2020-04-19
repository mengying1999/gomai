package com.gomai.goods.mapper;

import com.gomai.goods.pojo.Category2;
import com.gomai.goods.pojo.GoodsMedia;
import com.gomai.goods.vo.GoodsVo;
import com.gomai.goods.vo.Option;
import com.gomai.goods.vo.RecommendGoods;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface Category2Mapper extends Mapper<Category2> {
    @Select("select * from goods_category1")
     @Results(id = "findAllGoodsCategry",value = {@Result(id = true,column = "ca1_id",property = "value"),
                        @Result(column = "ca1_name",property = "label"),
                        @Result(column = "ca1_id",property = "children",many = @Many(select = "com.gomai.goods.mapper.Category2Mapper.findGoodsCategry2"))
               })
    List<Option> findAllGoodsCategry();//查询级联类别


    @Select("select ca2_id,ca2_name  from goods_category2 where ca1_id=#{ca1_id}")
     @Results(id = "findGoodsCategry2",value = {@Result(id = true,column = "ca2_id",property = "value"),
            @Result(column = "ca2_name",property = "label"),
    })
    List<Option> findGoodsCategry2(Integer ca1_id);//查询二级类别

    @Select("select ca2_id  from goods_category2 where ca1_id=#{ca1_id}")
    List<Integer> findCategoryTwoByOne(Integer ca1_id);////根据ca1_id查找对应的ca2类别
}
