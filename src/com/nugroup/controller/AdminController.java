package com.nugroup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nugroup.entity.GroupClass;
import com.nugroup.entity.User;
import com.nugroup.entity.UserDetail;
import com.nugroup.service.ClassService;
import com.nugroup.service.LoginService;
import com.nugroup.service.Userservice;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private Userservice userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private ClassService classService;

	@RequestMapping("/addClass")
	public String showAddClassForm() {
		return "class-form";
	}

	@RequestMapping("/processClass")
	public String processForm() {
		return "helloworld";
	}

	@PostMapping("/processlogin")
	public String authUser(HttpServletRequest request, Model theModel, @RequestParam("fEmail") String email,
			@RequestParam("fPassword") String password) {

		HttpSession session = request.getSession();
		User userDB = loginService.getUser(email);

		email = email.trim();
		password = password.trim();
		if (email.equals(null) || email.equals("") || password.equals(null) || password.equals("")) {
			theModel.addAttribute("errormsg", "Enter all required");
			return "login";
		}
		if (userDB == null) {
			theModel.addAttribute("errormsg", "Email not found");
			return "login";

		}

		if (userDB.getEmail().equals(email) && userDB.getPassword().equals(password)) {
			session.setAttribute("loggedUser", userDB);

			if (userDB.getUserDetail().getRole().equals("Student")) {
				return "studenthome";
			}
			if (userDB.getUserDetail().getRole().equals("Admin")) {
				return "adminhome";

			}

		}
		theModel.addAttribute("errormsg", "Incorrect email or password");

		// theModel.addAttribute("errormsg2", "Incorrect user name or password");
		return "login";

	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/userhome";

	}

	@GetMapping("/profilepage")
	public String profilePage(HttpServletRequest request, Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		if (u != null) {
			if (u.getUserDetail().getRole().equals("Admin")) {
				theModel.addAttribute("userdetails", u);
				theModel.addAttribute("enabler", false);
				return "profilepage";
			}

			return "access-denied";
		}

		return "login";

	}

	@GetMapping("/scheduleclass")
	public String scheduleClass(HttpServletRequest request, @ModelAttribute("groupClass") GroupClass theGroupClass,
			Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if (u != null) {
			if (u.getUserDetail().getRole().equals("Admin")) {
				GroupClass GC = new GroupClass();

				theModel.addAttribute("theGroupClass", GC);

				return "class-form";

			}

			return "access-denied";
		}

		return "login";

	}

	// saveClass
	@PostMapping("/saveClass")
	public String saveClass(HttpServletRequest request,
			@Validated @ModelAttribute("groupClass") GroupClass theGroupClass, BindingResult theBindingResult,
			Model theModel) {
		if (theBindingResult.hasErrors()) {
			System.out.println("Working binding variables");
			return "class-form";

		} else {
			theGroupClass.setName(theGroupClass.getName().trim());
			theGroupClass.setInstructor(theGroupClass.getInstructor().trim());
			theGroupClass.setLocation(theGroupClass.getLocation().trim());
			theGroupClass.setCapacity(100);

			if (theGroupClass.getName().equals("") || theGroupClass.getName().equals(null)) {
				theModel.addAttribute("emsg", "Class Name can't be empty");
				return "class-form";
			} else if (!theGroupClass.getName().matches("^[a-zA-Z0-9 ]*$")) {
				theModel.addAttribute("emsg", "Only alpha Numerics allowed");
				return "class-form";
			} else if (theGroupClass.getName().length() <= 3) {
				theModel.addAttribute("emsg", "Atleast 3 characters for Cult Name");
				return "class-form";
			}
			if (theGroupClass.getInstructor().equals("") || theGroupClass.getInstructor().equals(null)) {
				theModel.addAttribute("emsg", "Instructor can't be empty");
				return "class-form";
			} else if (!theGroupClass.getInstructor().matches("^[\\p{L} .'-]+$")) {
				theModel.addAttribute("emsg", "Instructor name can only have alphabets and spaces");
				return "class-form";
			} else if (theGroupClass.getInstructor().length() <= 3) {
				theModel.addAttribute("emsg", "Atleast 3 characters for Instructor name");
				return "class-form";
			}
			if (theGroupClass.getLocation().equals("") || theGroupClass.getLocation().equals(null)) {
				theModel.addAttribute("emsg", "Location can't be empty");
				return "class-form";
			} else if (theGroupClass.getLocation().length() <= 3) {
				theModel.addAttribute("emsg", "Atleast 3 characters for Location");
				return "class-form";
			}

			int i = classService.addORsaveClass(theGroupClass);
			if (i == 0) {
				System.out.println("Working binding variables");
				return "redirect:showclasses";
			} else {
				theModel.addAttribute("emsg", "Unable to save the data int database");
				return "class-form";

			}

		}

	}

	// deleteclass
	@GetMapping("/delete")
	public String deleteClass(Model theModel, @RequestParam("classId") int class_id) {
		int i = classService.deleteClass(class_id);
		if (i == 0) {
			return "redirect:showclasses";
		} else {
			theModel.addAttribute("emsg", "Deletion failed");
			List<GroupClass> classes = classService.getClasses();
			theModel.addAttribute("classes", classes);
			return "admin-class-list";
		}
	}

	@GetMapping("/showclasses")
	public String viewAllClasses(HttpServletRequest request, Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if (u != null) {

			if (u.getUserDetail().getRole().equals("Admin")) {

				List<GroupClass> classes = classService.getClasses();
				theModel.addAttribute("classes", classes);

				return "admin-class-list";
			}

			return "access-denied";

		}
		return "login";
	}

	@GetMapping("/updateclass")
	public String showFormForUpdate(@RequestParam("classId") int class_id, Model theModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if (u != null) {
			if (u.getUserDetail().getRole().equals("Admin")) {
				GroupClass GC = classService.getClass(class_id);
				theModel.addAttribute("groupClass", GC);
				return "class-form";
			}
			return "access-denied";

		}

		return "login";
	}

//	enableuser

	@GetMapping("/enableuser")
	public String enableUser(@RequestParam("userId") int user_id, Model theModel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if (u != null) {
			if (u.getUserDetail().getRole().equals("Admin")) {
				User uDB = userService.getUser(user_id);
				uDB.setEnabled(1);
				userService.updateUser(uDB);
				// GroupClass GC = classService.getClass(class_id);
				// theModel.addAttribute("groupClass", GC);

				return "redirect:waitlist";
			}
			return "access-denied";

		}

		return "login";
	}

//	@GetMapping("/showFormForUpdate")
//	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel) {
//		//get the customer from the service
//		Customer theCustomer =customerService.getCustomers(theId);
//		//set model attribute
//		theModel.addAttribute("customer", theCustomer);
//		//send attribute
//		return "customer-form";
//	}

	@GetMapping("/updateUser")
	public String updateUser(HttpServletRequest request, Model theModel,
			@ModelAttribute("userdetails") User updatedUser) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		if (u != null) {
			updatedUser.setFirstName(updatedUser.getFirstName().trim());
			updatedUser.setLastName(updatedUser.getLastName().trim());
			updatedUser.setPassword(updatedUser.getPassword().trim());

			if (updatedUser.getFirstName().equals("") || updatedUser.getFirstName().equals(null)) {

				theModel.addAttribute("error", "First name can't be empty");
				return "profilepage";
			} else if (!updatedUser.getFirstName().matches("^[\\p{L} .'-]+$")) {
				theModel.addAttribute("error", "First name can only have letters and spaces");
				return "profilepage";
			} else if (updatedUser.getFirstName().length() <= 3) {
				theModel.addAttribute("error", "Min 3 characters required for First Name");
				return "profilepage";
			}
			if (updatedUser.getLastName().equals("") || updatedUser.getLastName().equals(null)) {
				theModel.addAttribute("error", "Last name can't be empty");
				return "profilepage";
			} else if (!updatedUser.getLastName().matches("^[\\p{L} .'-]+$")) {
				theModel.addAttribute("error", "Last name can only have letters and spaces");
				return "profilepage";
			} else if (updatedUser.getLastName().length() <= 3) {
				theModel.addAttribute("error", "Min 3 characters required for Last Name");
				return "profilepage";
			}
			if (updatedUser.getPassword().equals("") || updatedUser.getPassword().equals(null)) {
				theModel.addAttribute("error", "Password name can't be empty");
				return "profilepage";
			} else if (updatedUser.getPassword().length() <= 6) {
				theModel.addAttribute("error", "Password should atleast be 6 characters");
				return "profilepage";
			}

			theModel.addAttribute("userdetails", u);
			System.out.println("printing user id" + u.getId());
			u.setFirstName(updatedUser.getFirstName());
			u.setLastName(updatedUser.getLastName());
			u.setPassword(updatedUser.getPassword());

			int i = userService.updateUser(u);

			if (i == -1) {
				theModel.addAttribute("error", "Error updating the userdetails in database");

				return "profilepage";
			} else {

				theModel.addAttribute("successmsg", "Updated successfully");
				return "profilepage";

			}

		}

		return "login";

	}

	@GetMapping("/viewstats")
	public String viewStats(HttpServletRequest request, Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		if (u != null) {
			if (u.getUserDetail().getRole().equals("Admin")) {

				List<User> totalUsers = userService.getUsers();
				List<User> admins = new ArrayList<User>();
				List<User> studs = new ArrayList<User>();
				for (User ul : totalUsers) {
					if(ul.getEnabled()==1) {
						if (ul.getUserDetail().getRole().equals("Admin")) {
							admins.add(ul);
						}
						else {studs.add(ul);}
					}
					
				}

				List<GroupClass> totalClasses = classService.getClasses();

				theModel.addAttribute("totalUs", totalUsers);

				theModel.addAttribute("totalCs", totalClasses);

				theModel.addAttribute("admins", admins);

				theModel.addAttribute("studs", studs);

				theModel.addAttribute("activeU", totalUsers.size());

				theModel.addAttribute("activeC", totalClasses.size());

				return "statsPage";
			}

			return "access-denied";

		}
		return "login";
	}

	@GetMapping("addadmin")
	public String showRegistration(HttpServletRequest request, Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if (u != null) {
			if (u.getUserDetail().getRole().equals("Admin")) {
				return "registration-form";
			}
			return "access-denied";
		}

		return "login";

	}
//	
//	<form:option value="Monday" label="Monday" />
//	<form:option value="Tuesday" label="Tuesday" />
//	<form:option value="Wednesday" label="Wednesday" />
//	<form:option value="Thursday" label="Thursday" />
//	<form:option value="Friday" label="Friday" />

	@GetMapping("charts")
	public String showCharts(HttpServletRequest request,Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		int mon=0,tue=0,wed=0,thu=0,fri=0,inU=0,aU=0;
		List<User> totalU = userService.getUsers();
		for(User oU:totalU) {
			if(oU.getUserDetail().getRole().equalsIgnoreCase("Student")) {
				List<GroupClass> schedule = userService.getSchedule(oU);
				if(schedule.size()>0) {
					aU++;
				}
				inU++;
			}
			
		}
		
		
		
		if(u!=null) {
			if(u.getUserDetail().getRole().equals("Admin")) {
				List<GroupClass> totalC = classService.getClasses();
				for(GroupClass gc:totalC){
					if(gc.getDay().equalsIgnoreCase("Monday")) {
						mon++;
					}else if(gc.getDay().equalsIgnoreCase("Tuesday")) {
						tue++;
					}
else if(gc.getDay().equalsIgnoreCase("Wednesday")) {
						wed++;
					}
else if(gc.getDay().equalsIgnoreCase("Thursday")) {
	thu++;
}
else if(gc.getDay().equalsIgnoreCase("Friday")) {
	fri++;
}
				}
				theModel.addAttribute("monday", mon);
				theModel.addAttribute("tuesday", tue);
				theModel.addAttribute("wednesday", wed);
				theModel.addAttribute("thursday", thu);
				theModel.addAttribute("friday", fri);
				theModel.addAttribute("totalU", inU);
				theModel.addAttribute("activeU", aU);
				
				
				
				return "charts";
			}
			return "access-denied";
		}
		
		return "login";
		
	}

//	
	@GetMapping("/waitlist")
	public String waitlist(HttpServletRequest request, Model theModel) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if (u != null) {
			if (u.getUserDetail().getRole().equals("Admin")) {
				List<User> totalU = userService.getUsers();
				List<User> waitlisted = new ArrayList<User>();
				for (User du : totalU) {
					if (du.getEnabled() == 0) {
						waitlisted.add(du);
					}
				}
				theModel.addAttribute("waitlisted", waitlisted);

				return "waitlist";
			}
			return "access-denied";
		}

		return "login";

	}

	@PostMapping("saveUser")
	public String saveUser(Model theModel, @RequestParam("fFirstName") String fFirstName,
			@RequestParam("fLastName") String fLastName, @RequestParam("fEmail") String fEmail,
			@RequestParam("fUserName") String fUserName, @RequestParam("fPassword") String fPassword) {
		User regUser = new User();
		String email_regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Pattern pattern =Pattern.compile(email_regex);
		Matcher matcher = pattern.matcher(fEmail);

		fFirstName = fFirstName.trim();
		fLastName = fLastName.trim();
		fEmail = fEmail.trim();
		fUserName = fUserName.trim();
		fPassword = fPassword.trim();
		boolean flag= false;
		if (fFirstName.equals("") || fFirstName.equals(null)) {
			theModel.addAttribute("errormsg", "first name can't be empty");
			flag=true;
			return "registration-form";
		} else if (!fFirstName.matches("^[a-zA-Z]*$")) {
			theModel.addAttribute("errormsg", "First Name Can only have alphabets");
			flag=true;
			return "registration-form";
		} else if (fFirstName.length() <= 3) {
			theModel.addAttribute("errormsg", "First Name Can't be less than 3 characters");
			flag=true;
			return "registration-form";
		}

		if (fLastName.equals("") || fLastName.equals(null)) {
			theModel.addAttribute("errormsg", "Last name can't be empty");
			flag=true;
			return "registration-form";
		} else if (!fFirstName.matches("^[a-zA-Z]*$")) {
			theModel.addAttribute("errormsg", "Last name Can only have alphabets");
			flag=true;
			return "registration-form";
		} else if (fLastName.length() <= 2) {
			theModel.addAttribute("errormsg", "Last Name Can't be less than 2 characters");
			flag=true;
			return "registration-form";
		}
		if (fEmail.equals("") || fEmail.equals(null)) {
			theModel.addAttribute("errormsg", "email can't be empty");
			flag=true;
			return "registration-form";
		}
		else if(! matcher.matches() ) {
			theModel.addAttribute("errormsg", "enter a valid email");
			
			flag=true;
			return "registration-form";
		}
		if (fUserName.equals("") || fUserName.equals(null)) {
			theModel.addAttribute("errormsg", "User name can't be empty");
			flag=true;
			return "registration-form";
		} else if (fUserName.length() <= 3) {
			theModel.addAttribute("errormsg", "User Name Can't be less than 3 characters");
			flag=true;
			return "registration-form";
		}

		if (fPassword.equals("") || fPassword.equals(null)) {
			theModel.addAttribute("errormsg", "Password can't be empty");
			flag=true;
			return "registration-form";
		} else if (fPassword.length() <= 6) {
			theModel.addAttribute("errormsg", "U Can't be less than 6 characters");
			flag=true;
			return "registration-form";
		}

		List<User> dbUsers = userService.getUsers();
		for (User u : dbUsers) {
			if (u.getEmail().equals(fEmail)) {
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
		regUser.setEnabled(1);
		UserDetail ud = new UserDetail();
		ud.setRole("Admin");
		regUser.setUserDetail(ud);
		if(!flag) {
		userService.updateUser(regUser);}

		return "/adminhome";
	}

}
