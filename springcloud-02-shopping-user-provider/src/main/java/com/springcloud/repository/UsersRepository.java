package com.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springcloud.entity.Users;

/**
 * 持久化层，定义对users表进行操作的方法
 * 
 * @author 201607090134-王若恒
 *
 */
public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

	/**
	 * 判断用户是否登录成功
	 * 
	 * @param userId
	 *            用户编号
	 * @param userPsw
	 *            用户密码
	 * @param permissionId
	 *            成功返回登录用户完整的信息，失败返回null
	 * @return
	 */
	@Query("select new com.springcloud.entity.Users(u.userId,u.userName,u.userIdnum,u.userPsw,u.userSex,u.userTel,u.userBirthday,u.userEmail,u.userImage,u.permissionId,u.userAddr,u.userStatus) "
			+ "from com.springcloud.entity.Users u where u.userId=:userId and u.userPsw=:userPsw and u.permissionId=:permissionId and u.userStatus=0")
	public abstract Users login(@Param("userId") Integer userId, @Param("userPsw") String userPsw,
			@Param("permissionId") Integer permissionId);

	/**
	 * 修改指定编号的用户的状态
	 * 
	 * @param userId
	 *            用户的Id
	 * @param userStatus
	 *            用户状态
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	@Modifying
	@Query("update Users set userStatus=:userStatus where userId=:userId")
	public abstract Integer updateStatus(@Param("userId") Integer userId, @Param("userStatus") Integer userStatus);

	@Modifying
	@Query("update Users u set u.userIdnum=:#{#users.userIdnum},u.userSex=:#{#users.userSex},u.userBirthday=:#{#users.userBirthday},u.userTel=:#{#users.userTel},u.userEmail=:#{#users.userEmail},u.userAddr=:#{#users.userAddr} where u.userId=:#{#users.userId}")
	public abstract Integer updateUser(@Param("users") Users users);

	/**
	 * 修改USERS表中指定USER_ID的用户头像
	 * 
	 * @param users
	 *            修改用户的信息
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	@Modifying
	@Query("update Users u set u.userImage=:#{#users.userImage} where u.userId=:#{#users.userId}")
	public abstract Integer updateImage(@Param("users") Users users);

	/**
	 * 修改USERS表中指定USER_ID的用户密码
	 * 
	 * @param users
	 *            修改用户信息
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	@Modifying
	@Query("update Users u set u.userPsw=:#{#users.userPsw} where u.userId=:#{#users.userId}")
	public abstract Integer updateUserPsw(@Param("users") Users users);

	/**
	 * 修改USERS表中指定USER_ID的用户昵称
	 * 
	 * @param users
	 *            修改用户信息
	 * @return 成功返回一个大于0的整数，否则返回0
	 */
	@Modifying
	@Query("update Users u set u.userName=:#{#users.userName} where u.userId=:#{#users.userId}")
	public abstract Integer updateUserName(@Param("users") Users users);
}
