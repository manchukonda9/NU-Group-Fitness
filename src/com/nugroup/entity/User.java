package com.nugroup.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_nugroup")
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@NotNull(message="is required")
	@Size(min=2, message="minimum of 3 characters")
	@Column(name="first_name")
	private String firstName;
	
	@NotNull(message="is required")
	@Size(min=2, message="minimum of 3 characters")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message="is required")
	@Column(name="user_name")
	private String userName;
	
	
	

	@NotNull(message="is required")
	@Pattern(regexp="^(.+)@(.+)$" ,message="Valid email only")
	@Column(name="email")
	private String email;
	
	@NotNull(message="is required")
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled;
	

	
	@OneToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name="user_role_nugroup_id")
	private UserDetail userDetail;
	
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="group_class_user",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="class_id")
			)
	private List<GroupClass> classes;
	
	
	public User() {
		
	}

	public User(String firstName, String lastName, String userName, String email, String password, int enabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	

	public List<GroupClass> getClasses() {
		return classes;
	}

	public void setClasses(List<GroupClass> classes) {
		this.classes = classes;
	}
	
	public void addClass(GroupClass theGroupClass){
		if(classes==null) {
			classes = new ArrayList<>();
		}
		classes.add(theGroupClass);
	}
	
	public void removeClass(GroupClass theGroupClass) {
		System.out.println("Inside remove Class");
		classes.remove(theGroupClass);
		System.out.println("after removing class");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", userDetail=" + userDetail
				+ "]";
	}
	

}
