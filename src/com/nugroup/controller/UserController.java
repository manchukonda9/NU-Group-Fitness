package com.nugroup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;
import com.nugroup.service.ClassService;
import com.nugroup.service.LoginService;
import com.nugroup.service.Userservice;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private Userservice userService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/profilepage")
	public String profilePage(HttpServletRequest request,Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		
		if(u!=null) {
			theModel.addAttribute("userdetails",u);
			theModel.addAttribute("enabler",false);
			return "profilepage";
			
		}
		
		return "login";
		
	}
	
	@PostMapping("/processlogin")
	public String authUser(HttpServletRequest request,Model theModel,@RequestParam("fEmail") String email,@RequestParam("fPassword") String password)
	{
		
		HttpSession session = request.getSession();
		User userDB = loginService.getUser(email);
	
		email = email.trim();
		password =password.trim();
		if(email.equals(null)|| email.equals("") || password.equals(null)|| password.equals("") ){
			theModel.addAttribute("errormsg", "Enter all required");
			return "login";
			}
		if(userDB==null) {
			theModel.addAttribute("errormsg", "Email not found");
			return "login";
			
		}

if(userDB.getEmail().equals(email) && userDB.getPassword().equals(password)) {
	session.setAttribute("loggedUser", userDB);
	
	if(userDB.getUserDetail().getRole().equals("Student")) {
		return "studenthome";
	}
	if(userDB.getUserDetail().getRole().equals("Admin")) {
		return "adminhome";
	
	
}


}
	theModel.addAttribute("errormsg","Incorrect email or password");


	//theModel.addAttribute("errormsg2", "Incorrect user name or password");
	return "login";




	}

	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/userhome";
		
	}
	
	@GetMapping("/deletAccount")
	public String deleteAccoount(HttpServletRequest request) {
		
		HttpSession session = request.getSession();

		User u = (User) session.getAttribute("loggedUser");
		userService.deleteUser(u);
		session.invalidate();
		return "redirect:/userhome";
		
	}
	
	
	@GetMapping("/updateUser")
	public String updateUser(HttpServletRequest request,Model theModel,@ModelAttribute("userdetails") User updatedUser ) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		
		if(u!=null) {
			
			updatedUser.setFirstName(updatedUser.getFirstName().trim());
			updatedUser.setLastName(updatedUser.getLastName().trim());
			updatedUser.setPassword(updatedUser.getPassword().trim());
			
			if(updatedUser.getFirstName().equals("")||updatedUser.getFirstName().equals(null)) {
				
				theModel.addAttribute("error", "First name can't be empty");
				return "profilepage";
			}
			else if(!updatedUser.getFirstName().matches("^[\\p{L} .'-]+$")) {
				theModel.addAttribute("error", "First name can only have letters and spaces");
				return "profilepage";
			}else if(updatedUser.getFirstName().length()<=3) {
				theModel.addAttribute("error", "Min 3 characters required for First Name");
				return "profilepage";
			}
			if(updatedUser.getLastName().equals("")||updatedUser.getLastName().equals(null)) {
				theModel.addAttribute("error", "Last name can't be empty");
				return "profilepage";
			}
			else if(!updatedUser.getLastName().matches("^[\\p{L} .'-]+$")) {
				theModel.addAttribute("error", "Last name can only have letters and spaces");
				return "profilepage";
			}
			else if(updatedUser.getLastName().length()<=3) {
				theModel.addAttribute("error", "Min 3 characters required for Last Name");
				return "profilepage";
			}
			if(updatedUser.getPassword().equals("")||updatedUser.getPassword().equals(null)) {
				theModel.addAttribute("error", "Password name can't be empty");
				return "profilepage";
			}else if(updatedUser.getPassword().length()<=6) {
				theModel.addAttribute("error", "Password should atleast be 6 characters");
				return "profilepage";
			}
			
			
			theModel.addAttribute("userdetails",u);
			System.out.println("printing user id"+u.getId());
			u.setFirstName(updatedUser.getFirstName());
			u.setLastName(updatedUser.getLastName());
			u.setPassword(updatedUser.getPassword());
			
			int i = userService.updateUser(u);
			
			if(i==-1) {
				theModel.addAttribute("error", "Error updating the userdetails in database");
				
				return "profilepage";
			}
			else {
			
			theModel.addAttribute("successmsg", "Updated successfully");
			return "profilepage";
			
			}
			
		}
		
		return "login";
		
	}
	
	
	@GetMapping("/showclasses")
	public String viewAllClasses(HttpServletRequest request,Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if(u!=null) {
			List<GroupClass> classes = classService.getClasses();
			theModel.addAttribute("classes",classes);
//			for(GroupClass cla:classes) {
//				System.out.println(cla.getName());
//				System.out.println(cla.getLocation());
//				
//			}
		
		return "classeslist";
		}
		return "login";
	}
	

	
	@GetMapping("/registerclass")
	public String registerForClass(@RequestParam("classId") int theClassId,Model theModel,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		
		if(u!=null) {
			User uDb = loginService.getUser(u.getEmail());
			List<GroupClass> regcls = userService.getSchedule(uDb);
			List<GroupClass> classes = classService.getClasses();
			GroupClass cDets = classService.getClass(theClassId);
			
			System.out.println(cDets);
			for(GroupClass gc:regcls) {
				
				if(gc.getId() == theClassId ) {
					theModel.addAttribute("classes",classes);
					theModel.addAttribute("emsg", "Something went wrong with database you must have already registered");
					return "classeslist";
				}
				
				if(gc.getDay().equalsIgnoreCase(cDets.getDay()) && gc.getTime().equalsIgnoreCase(cDets.getTime())) {
					theModel.addAttribute("classes",classes);
					theModel.addAttribute("emsg", "Time conflick error there is already a class scheduled for that time");
					return "classeslist";
				}
			}
			int q=-1;
			if(cDets.getCapacity()>0) {
			q = userService.addClass(theClassId, uDb);
			cDets.setCapacity(cDets.getCapacity()-1);
			classService.addORsaveClass(cDets);
			}else {
				theModel.addAttribute("emsg", "Seats are full for the class you selected");
				theModel.addAttribute("classes",classes);
				return "classeslist";
			}
			

			if(q==0) {
				
				theModel.addAttribute("classes",classes);
				theModel.addAttribute("qmsg", "Class successfully added");
			}else {
				//List<GroupClass> classes = classService.getClasses();
				theModel.addAttribute("emsg", "Something went wrong with database you must have already registered");
				theModel.addAttribute("classes",classes);
			}
			
			return "classeslist";
		}
		return "login";
	}
@GetMapping("/viewclasses")
public String scheduledClasses(Model theModel,HttpServletRequest request) {
	HttpSession session = request.getSession();
	User u = (User) session.getAttribute("loggedUser");
	if(u!=null) {
		User uDb = loginService.getUser(u.getEmail());
		List<GroupClass> regcls = userService.getSchedule(uDb);
		if(regcls != null) {
		theModel.addAttribute("classes",regcls);
		
		return "schedule";
		}
		else {
			theModel.addAttribute("emsg", "No classes registered");
			
			return "schedule";
		}
	}
	return "login";
}
	
	


@GetMapping("/deleteClass")
public String deleteCustomer(@RequestParam("classId") int theClassId,Model theModel,HttpServletRequest request) {
	HttpSession session = request.getSession();
	User u = (User) session.getAttribute("loggedUser");
	if(u!=null) {
		
	int q = userService.dropClass(theClassId, u);
	User uDb = loginService.getUser(u.getEmail());
	GroupClass cDets = classService.getClass(theClassId);
	cDets.setCapacity(cDets.getCapacity()+1);
	classService.addORsaveClass(cDets);
	List<GroupClass> regcls = userService.getSchedule(uDb);
	if(q==0) {
		theModel.addAttribute("qmsg", "Dropped class Successfully");
		if(regcls != null) {
			theModel.addAttribute("classes",regcls);
		}
		return "schedule";
	}
	else {
		theModel.addAttribute("emsg", "Error dropping the class");
		return "schedule";
	}
			}
	return "redirect:/user/schedule";
}



}
