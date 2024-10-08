package com.example.springsecuirtydemo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsecuirtydemo2.models.Userrights;


@Repository
public interface Userrepository extends JpaRepository<Userrights, Integer>{
	
	Userrights findByUsername(String username);

}
