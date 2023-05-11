package com.example.java_demo_test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String pwd;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "city")
	private String city;

	/*
	 * private Date regtime; 產生Date->new Date(); private LocalDateTime regtime;
	 * ->new LocalDateTime.now();
	 */

	@Column(name = "register_time")
	private LocalDateTime regTime = LocalDateTime.now(); // 資料庫欄位為date+time

	// boolean 生成會是is而非get 通常若資料型態為此 會在變數前新增"is"方便日後辨識

	@Column(name = "active")
	private boolean isActive;

	public Login() {
		
	}

	public Login(String account, String pwd, String name) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.name = name;
	}

	public Login(String account, String name, int age, String city) {
		super();
		this.account = account;
		this.name = name;
		this.age = age;
		this.city = city;
	}
	
	

	public Login(String account, String pwd, String name, int age, String city) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.city = city;
	}

	public Login(String account, String pwd, String name, int age, String city, LocalDateTime regTime,
			boolean isActive) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.city = city;
		this.regTime = regTime;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
