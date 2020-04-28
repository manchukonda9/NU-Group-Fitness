package com.nugroup.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nugroup.DAO.ClassDAO;
import com.nugroup.entity.GroupClass;


@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassDAO classDAO;

	@Transactional
//	@Cacheable("classlist")
	@Override
	public List<GroupClass> getClasses() {
		// TODO Auto-generated method stub
		return classDAO.getClasses();
	}

	@Transactional
	@Override
	public GroupClass getClass(int Id) {
		// TODO Auto-generated method stub
		return classDAO.getClass(Id);
	}

	@Transactional
	@Override
	public int deleteClass(int Id) {
		// TODO Auto-generated method stub
		return classDAO.deleteClass(Id);
	}

	@Transactional
	@Override
	public int addORsaveClass(GroupClass GC) {
		// TODO Auto-generated method stub
		return classDAO.addClass(GC);
	}

	@Override
	@Transactional
//	@Cacheable("classlist")
	public int getUsers(GroupClass gc) {
		// TODO Auto-generated method stub
		return classDAO.getUsersforClass(gc);
	}

}
