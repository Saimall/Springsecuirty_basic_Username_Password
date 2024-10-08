package com.example.springsecuirtydemo2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@PostMapping("/register")
	public Userrights addUserrights(@RequestBody Userrights userrights) {
		return userService.addUserrights(userrights);
	}
	
	@GetMapping("/getallusers")
	public List<Userrights> gelallUserrights(){
		return userService.getallUserrights();
	}

}
