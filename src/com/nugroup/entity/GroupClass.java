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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="group_class")
public class GroupClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="name")
	@NotNull(message="is required")
	@Size(min=2, message="minimum of 3 characters")
	private String name;
	
	@Column(name="time")
	private String time;
	
	
	
	@Column(name="instructor")
	@Pattern(regexp="^[a-zA-Z]*$" ,message="Alphabets only")
	private String instructor;
	

	@Column(name="location")
	@NotNull(message="is required")
	@Size(min=2, message="minimum of 3 characters")
	private String location;
	
	@Column(name="day")
	private String day;
	
	@Column(name="DURIATION")
	private String duration;
	
	@Column(name="Capacity")
	private int capacity;
	



	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="group_class_user",
			joinColumns = @JoinColumn(name="class_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
			)
	private List<User> users;
	
	
	public GroupClass() {
		
	}


	public GroupClass(String name, String time, String instructor, String location, String day, String duration) {
		super();
		this.name = name;
		this.time = time;
		this.instructor = instructor;
		this.location = location;
		this.day = day;
		this.duration = duration;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getInstructor() {
		return instructor;
	}


	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void addUser(User theUser) {
		if(users==null) {
			users = new ArrayList<>();
		}
		users.add(theUser);
	}


	@Override
	public String toString() {
		return "GroupClass [id=" + id + ", name=" + name + ", time=" + time + ", instructor=" + instructor
				+ ", location=" + location + ", day=" + day + ", duration=" + duration + "]";
	}
	
	

}
