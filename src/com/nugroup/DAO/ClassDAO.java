package com.nugroup.DAO;

import java.util.List;


import com.nugroup.entity.GroupClass;

public interface ClassDAO {
	
	public List<GroupClass> getClasses();
	
	public GroupClass getClass(int Id);
	
	public int deleteClass(int Id);
	
	public int addClass(GroupClass gc);
	
	public int getUsersforClass(GroupClass gc);
	


}
