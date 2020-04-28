package com.nugroup.DAO;

import java.util.Currency;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;


import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;


@Repository
public class UserDAOimpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUser(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		// TODO Auto-generated method stub
		try {
		
			User u = currentSession.get(User.class, id);
			if(u!=null) {
				return u;
			}
			else {
				return null;
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			
			System.out.println("id to be updated"+user.getId());
			System.out.println("lastname"+user.getLastName());
			currentSession.saveOrUpdate(user);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		
		}
		return 0;
	}

	@Override
	public int addClass(int classId, User u) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		try {
	
			System.out.println("In try block");
			GroupClass gc = currentSession.get(GroupClass.class, classId);
			gc.addUser(u);
		}
		catch(DataIntegrityViolationException e) {
			System.out.println("In catch block");
			e.printStackTrace();
			return -1;
		
		}
		catch(Exception e) {
			System.out.println("In catch block");
			e.printStackTrace();
			return -1;
		
		}
	
		return 0;
	}

	@Override
	public List<GroupClass> getSchedule(User u) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			List<GroupClass> regedClasses = u.getClasses();
			return regedClasses;
			
		}
		catch(Exception e) {
			return null;
		}
	
	}

	@Override
	public int dropClass(int classId, User u) {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			//Session currentSession = sessionFactory.getCurrentSession();
		
		User uDb = currentSession.get(User.class, u.getId());
		GroupClass gc = currentSession.get(GroupClass.class, classId);
		uDb.removeClass(gc);
		System.out.println("class dropped sucessfully");
//			Query q = currentSession.createQuery("DELETE FROM group_class_user WHERE class_id = :classId AND user_id =:userId");
//			
//			q.setParameter("classId", classId);
//			q.setParameter("userId",u.getId() );
		
			return 0;
		}
		catch(Exception e) {
			System.out.println("In catch remove class");
			return -1;
		}
		// TODO Auto-generated method stub
	
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		try {
//			Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",Customer.class);		
//			
//			//execute query and get result list
//			List<Customer> customers = theQuery.getResultList();
//			
//			// return the results
			Query<User> theQuery = currentSession.createQuery("from User",User.class);
			
			List<User> users = theQuery.getResultList();
			
			return users;
			
		}
		catch(Exception e) {
			System.out.println("Unable to get the users from the database");
			return null;
		}
	
	}

	@Override
	public int deleteUser(User u) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			currentSession.delete(u);
			System.out.println("Deleted user Succesfully");
		}
		catch(Exception e) {
			System.out.println("Unable to get the users from the database");
			return -1;
		}
		return 0;
	}

}
