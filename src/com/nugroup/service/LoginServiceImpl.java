package com.nugroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nugroup.DAO.LoginDAO;
import com.nugroup.entity.User;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	@Override
	@Transactional
	public User getUser(String email) {
		
		return loginDAO.getUser(email);
	}

}
