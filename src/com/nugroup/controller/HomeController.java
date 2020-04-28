package com.nugroup.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nugroup.entity.User;
import com.nugroup.entity.UserDetail;
import com.nugroup.service.Userservice;

@Controller
public class HomeController {
	@Autowired
	private Userservice userService;
	
	@RequestMapping("/")
	public String homePage() {
		
		//set the home page based on the user type
		return "main-menu";
	}
	
	@GetMapping("/userhome")
	public String userhomePage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User chUser = (User)session.getAttribute("loggedUser");
		if(chUser!=null && chUser.getUserDetail().getRole().equals("Student")) {
			return "studenthome";
		}
		
		
		
		//set the home page based on the user type
		return "login";
	}
	
	@GetMapping("/adminhome")
	public String adminhomePage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User chUser = (User)session.getAttribute("loggedUser");
		if(chUser!=null && chUser.getUserDetail().getRole().equals("Student")) {
			return "adminhome";
		}
		
		//set the home page based on the user type
		return "login";
	}
	
	@GetMapping("/login")
	public String loginPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("loggedUser");
		
		if(u!=null) {
			if(u.getUserDetail().getRole().equals("Student")) {
				return "studenthome";
			}
			return "adminhome";
			
		}
		
		
		//set the home page based on the user type
		return "login";
	}
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/userhome";
		
	}
	@GetMapping("/showRegistration")
	public String showRegistrationForm(Model theModel) {
	
		theModel.addAttribute("regUser", new User());
		
		return "registration-form";
	}
	@GetMapping("/blacksheep")
	public String blackSheep() {
		return "blacksheep";
	}
	
	@PostMapping("saveUser")
	public String saveUser(Model theModel,@RequestParam("fFirstName") String fFirstName,
			@RequestParam("fLastName") String fLastName,@RequestParam("fEmail") String fEmail,
			@RequestParam("fUserName") String fUserName,@RequestParam("fPassword") String fPassword
			) {
		String email_regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Pattern pattern =Pattern.compile(email_regex);
		Matcher matcher = pattern.matcher(fEmail);
		User regUser = new User();
		fFirstName = fFirstName.trim();
		fLastName = fLastName.trim();
		fEmail = fEmail.trim();
		fUserName = fUserName.trim();
		fPassword = fPassword.trim();
		boolean flag= false;
		if(fFirstName.equals("")||fFirstName.equals(null)) {
			theModel.addAttribute("errormsg", "first name can't be empty");
			flag=true;
			return "registration-form";
		}
		else if(!fFirstName.matches("^[a-zA-Z]*$")) {
			theModel.addAttribute("errormsg", "First Name Can only have alphabets");
			flag=true;
			return "registration-form";
		}else if(fFirstName.length()<=3) {
			theModel.addAttribute("errormsg", "First Name Can't be less than 3 characters");
			flag=true;
			return "registration-form";
		}
		
		if(fLastName.equals("")||fLastName.equals(null)) {
			theModel.addAttribute("errormsg", "Last name can't be empty");
			flag=true;
			return "registration-form";
		}
		else if(!fLastName.matches("^[a-zA-Z]*$")) {
			theModel.addAttribute("errormsg", "Last name Can only have alphabets");
			flag=true;
			return "registration-form";
		}
		else if(fLastName.length()<=2) {
			theModel.addAttribute("errormsg", "Last Name Can't be less than 2 characters");
			flag=true;
			return "registration-form";
		}
		if(fEmail.equals("")||fEmail.equals(null)) {
			theModel.addAttribute("errormsg", "email can't be empty");
			flag=true;
			return "registration-form";
		}
//		^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$
		else if(! matcher.matches() ) {
			theModel.addAttribute("errormsg", "enter a valid email");
			flag=true;
			return "registration-form";
		}
		
		if(fUserName.equals("")||fUserName.equals(null)) {
			theModel.addAttribute("errormsg", "User name can't be empty");
			flag=true;
			return "registration-form";
		}else if(fUserName.length()<=3) {
			theModel.addAttribute("errormsg", "User Name Can't be less than 3 characters");
			flag=true;
			return "registration-form";
		}
	
		if(fPassword.equals("")||fPassword.equals(null)) {
			theModel.addAttribute("errormsg", "Password can't be empty");
			flag=true;
			return "registration-form";
		}
		else if(fPassword.length()<=6) {
			theModel.addAttribute("errormsg", "Password Can't be less than 6 characters");
			flag=true;
			return "registration-form";
		}
		List<User> dbUsers =userService.getUsers();
		for(User u:dbUsers) {
			if(u.getEmail().equals(fEmail)) {
				theModel.addAttribute("errormsg", "This email already exists");
				flag=true;
				return "registration-form";
			}
		}
		
		regUser.setFirstName(fFirstName);
		regUser.setLastName(fLastName);
		regUser.setUserName(fUserName);
		regUser.setPassword(fPassword);
		regUser.setEmail(fEmail);
		regUser.setPassword(fPassword);
		UserDetail ud = new UserDetail();
		ud.setRole("Student");
		regUser.setUserDetail(ud);
		if(!flag) {
		userService.updateUser(regUser);}

		
		return "usermsg";
	}
	
	
}
