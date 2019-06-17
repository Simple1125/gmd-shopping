package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrderService;
import com.springcloud.vo.ResultValue;

/**
 * 订单模块的控制层
 * 
 * @author 201607090134-王若恒
 *
 */
@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 查询满足条件的订单信息
	 * 
	 * @param orders
	 *            查询条件
	 * @param pageNumber
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "/selectOrders")
	public ResultValue selectOrders(Orders orders, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			PageInfo<Orders> pageInfo = this.orderService.selectOrders(orders, pageNumber);
			List<Orders> list = pageInfo.getList();

			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("orderList", list);

				PageUtils pageUtils = new PageUtils(pageInfo.getPages() * 5);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);

				rv.setDataMap(map);

				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("查询失败！！！");

		return rv;
	}

	/**
	 * 修改指定编号的订单状态
	 * 
	 * @param orders
	 *            修改的订单信息
	 * @return
	 */
	@RequestMapping(value = "/updateOrdersStatus")
	public ResultValue updateOrdersStatus(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			Integer integer = this.orderService.updateOrdersStatus(orders);
			if (integer > 0) {
				rv.setCode(0);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("订单状态修改失败，请与管理员联系！！！");

		return rv;
	}

	/**
	 * 查询指定年月的销售额
	 * 
	 * @param orders
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "/selectOrderGroup")
	public ResultValue selectOrderGroup(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			List<Orders> list = this.orderService.selectOrderGroup(orders);
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();

				for (Orders orders1 : list) {
					x.add(orders1.getOrderMonth());
					y.add(orders1.getTotalSales());
				}

				Map<String, Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		return rv;
	}

}
