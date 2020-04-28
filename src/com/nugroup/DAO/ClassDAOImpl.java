package com.nugroup.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;

@Repository
public class ClassDAOImpl implements ClassDAO{
	
	@Autowired
	SessionFactory sessionFacotry;

	@Override
	public List<GroupClass> getClasses() {
		
		try {
		Session currentSession = sessionFacotry.getCurrentSession();
//		Query<Customer> theQuery = currentSession.createQuery("from Customer",Customer.class);
		Query<GroupClass> theQuery =  currentSession.createQuery("from GroupClass",GroupClass.class);
		
		List<GroupClass> onClasses = theQuery.getResultList();
		
		return onClasses;
	
		}
		catch(Exception e) {
			return new ArrayList<>();
			//e.printStackTrace();
			
		}
	}

	@Override
	public GroupClass getClass(int Id) {
			try {
			Session currentSession = sessionFacotry.getCurrentSession();
//			Query<Customer> theQuery = currentSession.createQuery("from Customer",Customer.class);
			GroupClass GC = currentSession.get(GroupClass.class, Id);
			
			return GC;
		
			}
			catch(Exception e) {
				return new GroupClass();
				//e.printStackTrace();
				
			}
	}

	@Override
	public int deleteClass(int Id) {
		// TODO Auto-generated method stub
		try {
			Session currentSession = sessionFacotry.getCurrentSession();
			GroupClass gc = currentSession.get(GroupClass.class, Id);
			currentSession.delete(gc);
			return 0;
			
		}
		catch(Exception e) {
			System.out.println("Something went wrong");
			return -1;
		}
	}

	@Override
	public int addClass(GroupClass gc) {
		// TODO Auto-generated method stub
		try {
			Session currentSession = sessionFacotry.getCurrentSession();
			currentSession.saveOrUpdate(gc);
			return 0;
			
		}
		catch(Exception e) {
			System.out.println("Something went wrong");
			return -1;
		}
	
	}

	@Override
	public int getUsersforClass(GroupClass gc) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFacotry.getCurrentSession();
		//System.out.println("In dao"+gc.getId());
		
		try {
		
			//GroupClass gcfromDb = currentSession.get(GroupClass.class, gc.getId());
			
			List<User> users =  gc.getUsers();
			
			return users.size();
			
			
		}catch(Exception e) {
			System.out.println("No classes");
			return 0;
		}

	}
	
	

	
	
	

}
