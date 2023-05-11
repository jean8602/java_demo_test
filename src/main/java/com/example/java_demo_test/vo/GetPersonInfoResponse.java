package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class GetPersonInfoResponse {

	private PersonInfo personInfo;

	private String message;
	
	private List<PersonInfo> personInfoList;
	
	

	public GetPersonInfoResponse(String message, List<PersonInfo> personInfoList) {
		super();
		this.message = message;
		this.personInfoList = personInfoList;
	}



	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}



	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}



	public GetPersonInfoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public GetPersonInfoResponse(String message) {
		super();
		this.message = message;
	}



	public GetPersonInfoResponse(PersonInfo personInfo, String message) {
		super();
		this.personInfo = personInfo;
		this.message = message;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
