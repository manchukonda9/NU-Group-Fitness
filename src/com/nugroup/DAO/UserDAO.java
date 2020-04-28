package com.nugroup.DAO;

import java.util.List;

import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;

public interface UserDAO {
	
	public User getUser(int id);
	
	public int saveUser(User user);
	
	public int addClass(int classId,User u);
	
	public List<GroupClass> getSchedule(User u);
	
	public int dropClass(int classId,User u);
	
	public List<User> getUsers();
	
	public int deleteUser(User u);
	
	

}
