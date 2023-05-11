package com.example.java_demo_test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "register")

public class Register {

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "reg_time")
	private LocalDateTime reg_time = LocalDateTime.now();

	@Column(name = "active")
	private boolean active;
	
	

	public Register(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
	}

	public Register() {

	}

	public Register(String account, String pwd, LocalDateTime reg_time, boolean active) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.reg_time = reg_time;
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

	public LocalDateTime getReg_time() {
		return reg_time;
	}

	public void setReg_time(LocalDateTime reg_time) {
		this.reg_time = reg_time;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}