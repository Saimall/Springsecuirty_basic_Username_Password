package com.example.springsecuirtydemo2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecuirtydemo2.Services.UserService;
import com.example.springsecuirtydemo2.models.Userrights;

@RestController
public class UserControllers {
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/register")
	public Userrights addUserrights(@RequestBody Userrights userrights) {
		return userService.addUserrights(userrights);
	}
	
	@GetMapping("/getallusers")
	public List<Userrights> gelallUserrights(){
		return userService.getallUserrights();
	}
	
	@PostMapping("/validate/user")
	public String validateuser(@RequestBody Userrights userrights) {
		
		System.out.println("users: "+userrights);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userrights.getPassword(),userrights.getUsername()));
		if(authentication.isAuthenticated()) {
			return  userService.getToken(userrights.getUsername());
					
		}
		return null;
		
		
		
	}
	
	
	@GetMapping("/validate/token")
	public boolean validatetoken(@RequestBody String token) {
		return userService.validatetoken(token);
	}
	


}
