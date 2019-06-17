package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValue;

/**
 * 控制层：接收视图层的数据，并调用模型层的相应的方法，将数据返回到视图层中
 * 
 * @author 201607090134-王若恒
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	/**
	 * 实现用户的登录功能
	 * 
	 * @param userId
	 *            用户编号
	 * @param userPsw
	 *            用户的密码
	 * @param permissionId
	 *            用户的权限
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPsw") String userPsw,
			@RequestParam("permissionId") Integer permissionId) {
		ResultValue rv = new ResultValue();

		try {
			Users login = this.usersService.login(userId, userPsw, permissionId);
			if (login != null) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户编号或密码不正确，或该账号已被禁用，请与管理员联系！");

		return rv;
	}

	/**
	 * 添加新的用户信息
	 * 
	 * @param users
	 *            新用户信息
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();

		try {
			Users insert = this.usersService.insert(users);
			if (insert != null) {
				rv.setCode(0);
				rv.setMessage("添加用户成功！");

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("添加用户失败！！！");

		return rv;
	}

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @param users
	 *            查询条件
	 * @param pageNumber
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "/select")
	public ResultValue select(Users users, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();

		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			// 获得分页的数据
			List<Users> list = page.getContent();
			// 判断是否查询到数据
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				// 将分页的数据添加到Map中
				map.put("userList", list);

				PageUtils pageUtils = new PageUtils((int) page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息添加到Map中
				map.put("pageUtils", pageUtils);
				// 将Map添加到ResultValue对象中
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
	 * 修改指定编号的用户的状态
	 * 
	 * @param userId
	 *            用户编号
	 * @param userStatus
	 *            用户状态
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	public ResultValue updateStatus(@RequestParam("userId") Integer userId,
			@RequestParam("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if (status > 0) {
				rv.setCode(0);
				rv.setMessage("用户状态修改成功！！！");

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("用户状态修改失败！！！");

		return rv;
	}

	/**
	 * 查询指定用户的信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	@RequestMapping(value = "/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			Users users = this.usersService.selectById(userId);
			if (users != null) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("users", users);
				rv.setDataMap(map);

				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("查询用户信息失败！！！");
		return rv;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param users
	 *            用户信息
	 * @return
	 */
	@RequestMapping(value = "/updateUser")
	public ResultValue updateUser(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateUser = this.usersService.updateUser(users);
			if (updateUser > 0) {
				rv.setCode(0);

				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("修改用户信息失败！！");
		return rv;
	}

	/**
	 * 修改用户头像
	 * 
	 * @param users
	 *            用户头像信息
	 * @return
	 */
	@RequestMapping(value = "/updateUserImage")
	public ResultValue updateUserImage(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Integer integer = this.usersService.updateUserImage(users);
			if (integer > 0) {
				rv.setCode(0);

				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像修改失败！！！");

		return rv;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param users
	 *            用户密码
	 * @return
	 */
	@RequestMapping(value = "/updateUserPsw")
	public ResultValue updateUserPsw(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Integer integer = this.usersService.updateUserPsw(users);
			if (integer > 0) {
				rv.setCode(0);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("用户密码修改失败！！");
		return rv;
	}

	/**
	 * 修改用户昵称
	 * 
	 * @param users
	 *            用户昵称
	 * @return
	 */
	@RequestMapping(value = "/updateUserName")
	public ResultValue updateUserName(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Integer integer = this.usersService.updateUserName(users);
			if (integer > 0) {
				rv.setCode(0);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("修改失败！！");
		return rv;
	}
}
