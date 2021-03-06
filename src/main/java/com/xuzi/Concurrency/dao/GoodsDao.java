package com.xuzi.Concurrency.dao;

import com.xuzi.Concurrency.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select g.* ,mg.stock_count,mg.start_date,mg.end_date,mg.miaosha_price,goods_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

}
