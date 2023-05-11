package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoRequest;
import com.example.java_demo_test.vo.PersonInfoResponse;

public interface PersonInfoService {

//	public void addPersonInfo(PersonInfo personInfo);//entity內建的四個屬性,對應到資料庫即一筆資料

	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList);// entity內建的四個屬性,多筆資料用list

	public PersonInfoResponse getPersonInfo();

	public GetPersonInfoResponse getPersonInfoById(String id);

//	public GetPersonInfoResponse getAllPersonInfo();

	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(PersonInfoRequest personInfoRequest);

	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(PersonInfoRequest personInfoRequest);
	
	public PersonInfoResponse getPersonInfoByAgeBetween(PersonInfoRequest personInfoRequest);
	
	public GetPersonInfoResponse getPersonInfoContaining (PersonInfoRequest personInfoRequest);
	
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(PersonInfoRequest personInfoRequest);

	
}
