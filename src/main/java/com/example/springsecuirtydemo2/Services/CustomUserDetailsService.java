package com.example.springsecuirtydemo2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecuirtydemo2.models.Userrights;
import com.example.springsecuirtydemo2.repository.Userrepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	Userrepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Userrights userrights = userrepository.findByUsername(username);
		if(userrights!=null) {
			System.out.println("user found");
			return new Userrightsservice(userrights);
		}
		throw new UsernameNotFoundException("Username Not found");
	}

}
