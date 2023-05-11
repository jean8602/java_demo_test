package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_demo_test.entity.Login;

public interface LoginDao extends JpaRepository<Login, String> {

	public List<Login> findByAccount(String account);

	public List<Login> findByCityAndNameContainingOrderByAge(String city, String name);
	
	public Login findByAccountAndPwd(String account, String password);

}
