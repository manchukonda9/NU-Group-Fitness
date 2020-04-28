package com.nugroup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="user_role_nugroup")
public class UserDetail {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="role")
	private String role;
	public UserDetail() {
		
	}
	
	public UserDetail(String role) {
		this.role = role;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", role=" + role + "]";
	}
	

	//annotate the class an entity and map to database
	
	//define fields
	
	//annontate with db
	
	//create cons
}
