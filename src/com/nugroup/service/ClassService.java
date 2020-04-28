package com.nugroup.service;

import java.util.List;

import com.nugroup.entity.GroupClass;

public interface ClassService {
	public List<GroupClass> getClasses();
	
	public GroupClass getClass(int Id);
	
	public int deleteClass(int Id);
	
	public int addORsaveClass(GroupClass GC);
	
	public int getUsers(GroupClass gc);

}
