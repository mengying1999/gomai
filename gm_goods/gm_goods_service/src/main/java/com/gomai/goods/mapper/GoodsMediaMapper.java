package com.gomai.goods.mapper;

import com.gomai.goods.pojo.GoodsMedia;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import javax.persistence.Column;

public interface GoodsMediaMapper extends Mapper<GoodsMedia>, InsertListMapper<GoodsMedia> {

    @Insert("insert into goods_media(gm_id,g_id,gm_url,gm_type) values(null,#gId,#gmUrl,#gName,#gmType)")
    boolean insertGoodsMedia(GoodsMedia goodsMedia);
}
