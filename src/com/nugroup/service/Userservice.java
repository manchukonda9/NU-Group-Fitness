package com.nugroup.service;

import java.util.List;

import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;



public interface Userservice {
	
	
	
	
	public User getUser(int Id);
	
	public int updateUser(User user);
	
	public int addClass(int ClassId,User u);
	
	public List<GroupClass> getSchedule(User u);
	
	public int dropClass(int classId,User u);
	
	public List<User> getUsers();
	
	public int deleteUser(User u);
	

}
