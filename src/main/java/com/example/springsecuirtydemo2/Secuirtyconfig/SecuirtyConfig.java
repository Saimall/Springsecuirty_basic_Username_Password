package com.example.springsecuirtydemo2.Secuirtyconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf->csrf.disable()).authorizeHttpRequests(request->request.requestMatchers("/register").permitAll().anyRequest().authenticated()).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
	
	
		return http.build();
	}
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		
		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
		
		return daoAuthenticationProvider;
	}


 



	

}
