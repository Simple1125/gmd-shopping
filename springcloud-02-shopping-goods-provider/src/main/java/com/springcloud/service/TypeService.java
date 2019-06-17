package com.springcloud.service;

import java.util.List;

import com.springcloud.entity.TypeOne;
import com.springcloud.entity.TypeTwo;

/**
 * 用于定义一级类别与二级类别模块的方法
 * 
 * @author 201607090134-王若恒
 *
 */
public interface TypeService {

	/**
	 * 查询一级类别的所有信息
	 * 
	 * @return 成功返回java.util.List的实例，否则返回空
	 */
	public abstract List<TypeOne> selectAll();

	/**
	 * 查询指定一级类别编号的所有二级类别的信息
	 * 
	 * @param typeOneId
	 *            类别一编号
	 * @return 成功返回java.util.List的实例，否则返回空
	 */
	public abstract List<TypeTwo> selectByTypeOneId(Integer typeOneId);
}
