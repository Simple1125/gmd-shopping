package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.TypeOne;
import com.springcloud.entity.TypeTwo;
import com.springcloud.service.TypeService;
import com.springcloud.vo.ResultValue;

/**
 * 一级类别与二级类别的控制器
 * 
 * @author 201607090134-王若恒
 *
 */
@RestController
@RequestMapping("type")
public class TypeController {

	@Autowired
	private TypeService typeService;

	/**
	 * 查询一级类别的所有信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectAll")
	public ResultValue selectAll() {
		ResultValue rv = new ResultValue();
		
		try {
			List<TypeOne> list = this.typeService.selectAll();
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("typeOneList", list);

				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		return rv;
	}

	/**
	 * 查询指定编号的一级类别的二级类别的所有信息
	 * 
	 * @param typeOneId
	 *            一级类别编号
	 * @return
	 */
	@RequestMapping(value = "/selectByTypeOneId")
	public ResultValue selectByTypeOneId(@RequestParam("typeOneId") Integer typeOneId) {
		ResultValue rv = new ResultValue();
		try {
			List<TypeTwo> list = this.typeService.selectByTypeOneId(typeOneId);
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("typeTwoList", list);

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
