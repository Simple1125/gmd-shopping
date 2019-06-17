package com.springcloud.service;

import org.springframework.data.domain.Page;

import com.springcloud.entity.Users;

/**
 * 模型层的接口，用于定义用户模块的方法
 * 
 * @author 201607090134-王若恒
 *
 */
public interface UsersService {

	/**
	 * 验证用户登录是否成功
	 * 
	 * @param userId
	 *            用户编号
	 * @param userPsw
	 *            用户密码
	 * @param permissionId
	 *            权限编号
	 * @return 成功返回登录用户的完整信息，失败返回null
	 */
	public abstract Users login(Integer userId, String userPsw, Integer permissionId);

	/**
	 * 添加新的用户信息
	 * 
	 * @param users
	 *            新的用户信息
	 * @return 添加成功，返回com.springcloud.entity.Users 类型的实例，否则返回null
	 */
	public abstract Users insert(Users users);

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @param users
	 *            查询条件
	 * @param pageNumber
	 *            查询的页数
	 * @return 成功返回org.springframework.data.domain.Page类的实例，否则返回空
	 */
	public abstract Page<Users> select(Users users, Integer pageNumber);

	/**
	 * 修改指定编号的用户的状态
	 * 
	 * @param userId
	 *            用户的Id
	 * @param userStatus
	 *            用户状态
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	public abstract Integer updateStatus(Integer userId, Integer userStatus);

	/**
	 * 查询指定编号的用户信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return 返回com.springcloud.entity.Users 类型的实例，否则返回null
	 */
	public abstract Users selectById(Integer userId);

	/**
	 * 修改指定编号用户信息
	 * 
	 * @param users
	 *            修改的用户信息
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	public abstract Integer updateUser(Users users);

	/**
	 * 修改指定用户编号的用户头像
	 * 
	 * @param users
	 *            修改的信息
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	public abstract Integer updateUserImage(Users users);

	/**
	 * 修改指定用户编号的用户密码
	 * 
	 * @param users
	 *            修改的信息
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	public abstract Integer updateUserPsw(Users users);

	/**
	 * 修改指定用户编号的用户昵称
	 * 
	 * @param users
	 *            修改的信息
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	public abstract Integer updateUserName(Users users);
}
