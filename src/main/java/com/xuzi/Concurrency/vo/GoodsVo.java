package com.xuzi.Concurrency.vo;

import com.xuzi.Concurrency.domain.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsVo extends Goods {

    private Double miaoshaPrice;

    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
