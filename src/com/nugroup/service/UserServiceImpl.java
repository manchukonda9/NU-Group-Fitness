package com.nugroup.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nugroup.DAO.UserDAO;
import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;


@Service
public class UserServiceImpl implements Userservice {
	
	@Autowired
	private UserDAO userDAO;
	


	@Override
	@Transactional
	public User getUser(int Id) {
		
		
	
		return userDAO.getUser(Id);
	}

	@Override
	@Transactional
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public int addClass(int ClassId, User u) {
		// TODO Auto-generated method stub
		return userDAO.addClass(ClassId,u);
	}

	@Override
	@Transactional
	public List<GroupClass> getSchedule(User u) {
		// TODO Auto-generated method stub
		return userDAO.getSchedule(u);
	}

	@Override
	@Transactional
	public int dropClass(int classId, User u) {
		// TODO Auto-generated method stub
		return userDAO.dropClass(classId, u);
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public int deleteUser(User u) {
		// TODO Auto-generated method stub
		return userDAO.deleteUser(u);
	}



}
