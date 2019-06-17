package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品表GOODS对应的实体类：用来保存一行商品的信息
 * 
 * @author 201607090134-王若恒
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements java.io.Serializable {

	private static final long serialVersionUID = -3427056522676684614L;

	/**
	 * 商品编号
	 */
	private Integer goodsId;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品价格
	 */
	private Double goodsPrice;

	/**
	 * 商品折扣价
	 */
	private Double goodsDiscount;

	/**
	 * 商品状态：0预售，1热卖中，2已下架
	 */
	private Integer goodsStatus;

	/**
	 * 商品数量
	 */
	private Integer goodsNum;

	/**
	 * 是否新品：0新品，1不是
	 */
	private Integer goodsNew;

	/**
	 * 是否热卖：0是，1不是
	 */
	private Integer goodsHot;

	/**
	 * 商品级别：0一星，1二星，2三星，3四星，4五星
	 */
	private Integer goodsLevel;

	/**
	 * 商品简介
	 * 
	 */
	private String goodsDesc;

	/**
	 * 商品详情
	 * 
	 */
	private String goodsDetail;

	/**
	 * 商品图片
	 */
	private String goodsImg;

	/**
	 * 类别二编号
	 */
	private Integer typeTwoId;

	/**
	 * 查询条件：商品价格下限
	 */
	private Double goodsPriceMin;

	/**
	 * 查询条件：商品价格上限
	 */
	private Double goodsPriceMax;

	/**
	 * 查询条件：一级类型类别
	 */
	private Integer typeOneId;

	/**
	 * 商品销量：用于保存统计分组的结果
	 */
	private Integer goodsSum;

}