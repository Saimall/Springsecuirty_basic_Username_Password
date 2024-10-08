package com.example.springsecuirtydemo2.Services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.springsecuirtydemo2.models.Userrights;


public class Userrightsservice implements UserDetails{
	
	
	
	Userrights userrights;

	public Userrightsservice(Userrights userrights) {
		
		this.userrights = userrights;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userrights.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userrights.getUsername();
	}
	
	


}
