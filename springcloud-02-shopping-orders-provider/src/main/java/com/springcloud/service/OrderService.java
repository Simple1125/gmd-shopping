package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Orders;

/**
 * 订单模块模型层的接口，用于定义订单模块的方法
 * 
 * @author 201607090134-王若恒
 *
 */
public interface OrderService {

	/**
	 * 查询满足条件的订单信息
	 * 
	 * @param orders
	 *            查询的条件
	 * @return 返回com.github.pagehelper.PageInfo<Orders>类型的实例
	 */
	public abstract PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber);

	/**
	 * 修改指定编号订单的订单状态
	 * 
	 * @param orders
	 *            修改的订单信息
	 * @return 成功返回大于0的整数，否则返回小于0的整数
	 */
	public abstract Integer updateOrdersStatus(Orders orders);

	/**
	 * 查询指定年月的销售额
	 * 
	 * @param orders
	 *            查询条件
	 * @return 成功返回java.util.List类型的实例，否则返回空
	 */
	public abstract List<Orders> selectOrderGroup(Orders orders);
}
