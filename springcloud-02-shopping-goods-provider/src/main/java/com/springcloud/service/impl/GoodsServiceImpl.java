package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.dao.GoodsMapper;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;

/**
 * 用于实现对商品模块的进行操作的方法
 * 
 * @author 201607090134-王若恒
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public Integer insert(Goods goods) {
		return this.goodsMapper.insert(goods);
	}

	@Override
	public PageInfo<Goods> select(Goods goods, Integer pageNumber) {
		// 在商品名称的两端加%%
		goods.setGoodsName("%" + goods.getGoodsName() + "%");
		// 设置分页信息
		PageHelper.startPage(pageNumber + 1, 12);
		// 查询满足条件的商品信息
		List<Goods> list = this.goodsMapper.select(goods);
		// 返回分页信息
		return new PageInfo<>(list);
	}

	@Transactional
	@Override
	public Integer updateGoodsById(Goods goods) {
		return this.goodsMapper.updateGoodsById(goods);
	}

	@Transactional
	@Override
	public Integer update(Goods goods) {
		return this.goodsMapper.updateByPrimaryKey(goods);
	}

	@Override
	public List<Goods> selectGroup() {
		return this.goodsMapper.selectGroup();
	}

}
