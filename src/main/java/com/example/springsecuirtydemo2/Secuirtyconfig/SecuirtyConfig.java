package com.example.springsecuirtydemo2.Secuirtyconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springsecuirtydemo2.Services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecuirtyConfig {
	
	//why autowirig userdtailsservice
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	
	//.httpbasic(customizer->customizer.withDefault()) -->this is used to The httpBasic(Customizer.withDefaults()) is typically used to configure HTTP Basic authentication for a web application.
	
	//.headers(headers->headers.frameoptions(frame->frame.withdefault()))
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf->csrf.disable()).authorizeHttpRequests(request->request.requestMatchers("/register","/validate/user","/validate/token").permitAll().anyRequest().authenticated()).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()).headers(header->header.frameOptions(frame->frame.sameOrigin())) ;
	
	
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationmanager( AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		
		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
		
		return daoAuthenticationProvider;
	}


 



	

}
