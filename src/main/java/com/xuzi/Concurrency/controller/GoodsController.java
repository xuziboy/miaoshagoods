package com.xuzi.Concurrency.controller;

import com.xuzi.Concurrency.domain.MiaoshaUser;
import com.xuzi.Concurrency.redis.RedisService;
import com.xuzi.Concurrency.service.ConUserService;
import com.xuzi.Concurrency.service.GoodsService;
import com.xuzi.Concurrency.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	ConUserService userService;
	
	@Autowired
	RedisService redisService;

	@Autowired
	private GoodsService goodsService;

    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser miaoshaUser){
    	model.addAttribute("user",miaoshaUser);
    	//查询商品列表
		List<GoodsVo> goodsDaoList = goodsService.listGoodsVo();
		model.addAttribute("goodsList",goodsDaoList);
		return "goods_list1";
	}
    
}
