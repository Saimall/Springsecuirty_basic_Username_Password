package com.example.springsecuirtydemo2.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springsecuirtydemo2.models.Userrights;
import com.example.springsecuirtydemo2.repository.Userrepository;

@Service
public class UserService {
	
	@Autowired
	private Userrepository userrepository;
	

	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	


	public Userrights addUserrights(Userrights userrights) {
		
		userrights.setPassword(passwordEncoder.encode(userrights.getPassword()));
		
		return userrepository.save(userrights);
	}
	
	
	public List<Userrights> getallUserrights(){
		return userrepository.findAll();
	}


	public String getToken(String username) {
		return JwtServices.generateToken(username);
	}
	
	public boolean validatetoken(String token) {
		return JwtServices.validateToken(token);
	}

}
