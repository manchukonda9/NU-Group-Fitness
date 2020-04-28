package com.nugroup.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nugroup.entity.User;

@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		try {
		Session currentSession = sessionFactory.getCurrentSession();
		Query q =currentSession.createQuery("from User u where u.email =:email");
		
		q.setParameter("email", email);
		
		User userObj = (User)q.uniqueResult();
		
		
		if(userObj!=null) {
		
		return userObj;
		}
		else {
			System.out.println("No object found");
			return null;
		}
		
		}
		catch(Exception e) {

			e.printStackTrace();
			
		}
		return null;
		
	}

}
