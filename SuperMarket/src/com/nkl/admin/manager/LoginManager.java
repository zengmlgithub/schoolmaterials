package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.Md5;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.User;

public class LoginManager {

	UserDao userDao = new UserDao();
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<Picnews>
	 */
	public List<User> listUsers(User user){
		Connection conn = BaseDao.getConnection();
		
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 查询用户
	 * @param user
	 * @return User
	 */
	public User getUser(User user){
		Connection conn = BaseDao.getConnection();
		Logger logger = Logger.getLogger(LoginManager.class);
		logger.info("conn:" + conn);
		user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		logger.info("post " + user.toString());
		User _user = userDao.getUser(user,conn);
		logger.info("get " + _user.toString());
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	
}
