package com.nugroup.demo;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;
import com.nugroup.entity.UserDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserDetail.class)
				.addAnnotatedClass(GroupClass.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		
		try {
			session.beginTransaction();
			
			//session.createNativeQuery("DELETE FROM group_class_user WHERE class_id = 31 AND user_id = 3");
				
		
		//		session.save(newUser);
			int studentId = 3;
			User u = session.get(User.class, studentId);
			GroupClass gc = session.get(GroupClass.class, 31);
			u.removeClass(gc);
			System.out.println("Deleted relation successfully");
				
				
				
			
				
				
				session.getTransaction().commit();
			
//			User u1 = session.get(User.class, 4);
			
//			User u2 = session.get(User.class, 14);
//			
//			User u3 = session.get(User.class, 15);
			
//			session.delete(u1);
			
//			session.delete(u2);
//			
//			session.delete(u3);
//			
			//String firstName, String lastName, String userName, String email, String password, int enabled
//			System.out.println("saving Course");
////			
//			User newUser1 = new User("susan","public","susy","susan@gmail.com","test123",1);
//			
//			UserDetail userDetail1 = new UserDetail("Admin");
//			
//			session.delete(newUser1);
//	
//			
//			newUser1.setUserDetail(userDetail1);
//			
//			session.save(newUser1);
//			
//			User newUser2 = new User("Ajay","Mehta","Mehka","ajay@gmail.com","test123",1);
//	
//			
//			UserDetail userDetail2 = new UserDetail("Admin");
//			
//			newUser2.setUserDetail(userDetail2);
//			
//			session.save(newUser2);
//			
//			User newUser = new User("mary","kom","kom","mary@gmail.com","test123",1);
//			
//			UserDetail userDetail = new UserDetail("Student");
//			
//			
//			GroupClass newGC0 = new GroupClass("Cardio","6'O Clock", "Yusuf", "Marino", "Monday","One hr");
//			GroupClass newGC1 = new GroupClass("Mat Pilates","6'O Clock", "Kal", "Cabot", "Tuesday","One hr");
//			GroupClass newGC2 = new GroupClass("Zumba","6'O Clock", "Ghasei", "YMC", "Wednesday","One hr");
//			GroupClass newGC3 = new GroupClass("Abs 60","6'O Clock", "Kal", "Marino", "Thursday","One hr");
//			GroupClass newGC4 = new GroupClass("Cycle 60","6'O Clock", "Emma", "YMC", "Friday","One hr");
//			GroupClass newGC5 = new GroupClass("Body Sculpt","6'O Clock", "Julia", "CAbot", "Saturday","One hr");
////		
//			
//			
//			session.save(newGC0);
//			session.save(newGC1);
//			session.save(newGC2);
//			session.save(newGC3);
//			session.save(newGC4);
//			session.save(newGC5);
//			System.out.println("Saved the class"+newGC5);
			
//			
//			
//			System.out.println("Saving student..");
//			newUser.setUserDetail(userDetail);
//			
//			newUser1.setUserDetail(userDetail1);
//			
//			newGC.addUser(newUser);
//			newGC.addUser(newUser1);
//			
//			
//			session.save(newUser);
////			session.save(newUser1);
//			String email = "marfy@gmail.com";
//			User u = session.get(User.class, 3);
//			Query q =session.createQuery("from User u where u.email =:email");
//			q.setParameter("email", email);
//			
			
//			
//			User sel_user = (User) q.uniqueResult();
			
//			System.out.println(u.getId());
//			if(sel_user!=null) {
//			System.out.println("from sel_user"+ sel_user.getFirstName());
//			
//			
//			}
//			else {
//				System.out.println("user not fount");
//			}
////			
//			System.out.println(selUser.getPassword());
	
			
//			
//						
//			// commit transaction
//			session.getTransaction().commit();
//			
//			System.out.println("Done!");
			
			
	
			/*getting the classes by id many to many mapping */
			
//			GroupClass newGC = new GroupClass("yoga","5'O Clock", "Yusuf", "Marino", "Monday","One hr");
//			
//				
//				
//				session.save(newGC);
//			newGC.addUser(newUser);
//			newGC.addUser(newUser1);
//			
//			int studentId = 3;
//			User u = session.get(User.class, studentId);		
////			System.out.println("\nLoaded student: " + tempStudent);
////		System.out.println("Registered classes: " + tempStudent.getClasses());	
////			
//		List<GroupClass> regedClasses = u.getClasses();
//		for(GroupClass gc1:regedClasses) {
//			if(11 == gc1.getId()) {
//				System.out.println("inside if");
//				System.out.println("Already registerered");
//				//theModel.addAttribute("emsg", "You have already registered for the class");
//			
//			}
//		}
//			
//			int studentId = 3;
//			User u = session.get(User.class, studentId);
//			GroupClass gc = session.get(GroupClass.class, 11);
//			u.removeClass(gc);
//			System.out.println("Deleted relation successfully");
//			
//		
			
//			System.out.println("Done!");
//			System.out.println(rUser.getUserDetail().getRole());
		}
		finally {
			factory.close();
		}
		
		

	}

}
