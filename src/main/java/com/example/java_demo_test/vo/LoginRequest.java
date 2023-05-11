package com.example.java_demo_test.vo;

import java.util.List;

import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;

import com.example.java_demo_test.entity.Login;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
	
//	@JsonProperty("login")
	
//	private List<Login> login;
	
	private Login login;
	
	private boolean isActive;
	
	private String account;
	
	private String pwd;
	
	private String city;
	
	private String name;
	
	private int age;
	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

//	public List<Login> getLogin() {
//		return login;
//	}
//
//	public void setLogin(List<Login> login) {
//		this.login = login;
//	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	
	
	

}
