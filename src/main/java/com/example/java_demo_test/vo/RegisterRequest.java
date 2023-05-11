package com.example.java_demo_test.vo;

public class RegisterRequest {
	
	private boolean active;

	private String account;

	private String pwd;
	
	private int verifyCode;

	public RegisterRequest(boolean active, String account, String pwd) {
		super();
		this.active = active;
		this.account = account;
		this.pwd = pwd;
	}

	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	

}
