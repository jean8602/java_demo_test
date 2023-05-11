package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Login;

public class LoginResponse {
	private String message;

	private String account;

	private String pwd;

	private String name;

	private List<Login> login;

	public LoginResponse(String message, String account, String pwd, String name) {
		super();
		this.message = message;
		this.account = account;
		this.pwd = pwd;
		this.name = name;
	}

	public LoginResponse(String message, String account) {
		super();
		this.message = message;
		this.account = account;
	}

	public LoginResponse() {

	}

	public LoginResponse(String message) {
		super();
		this.message = message;
	}

	public LoginResponse(String message, List<Login> login) {
		super();
		this.message = message;
		this.login = login;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Login> getLogin() {
		return login;
	}

	public void setLogin(List<Login> login) {
		this.login = login;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
