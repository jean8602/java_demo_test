package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoResponse {

	private String message;

	private List<PersonInfo> personInfoList;

	public PersonInfoResponse() {

	}

	public PersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public PersonInfoResponse(String message, List<PersonInfo> personInfoList) {
		super();
		this.message = message;
		this.personInfoList = personInfoList;
	}
	

	public PersonInfoResponse(List<PersonInfo> personInfoList) {
		super();
		this.personInfoList = personInfoList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}

}
