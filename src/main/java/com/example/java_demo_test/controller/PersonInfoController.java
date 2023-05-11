package com.example.java_demo_test.controller;

import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.AddPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoRequest;
import com.example.java_demo_test.vo.PersonInfoResponse;

//1. 先加這個註釋 2. 新增方法（從personInfoSerivce 複製）3. 方法上加上@PostMapping or @GetMapping
//4.將方法內部加上請求->到vo 新增PersonInfoRequest class 5.內部呼叫service 6.若沒加上＠RequestBody request都會是null
@RestController 

public class PersonInfoController {
	@Autowired
	private PersonInfoService personInfoService;

	@GetMapping(value = "get_Person_Info")
	public PersonInfoResponse getPersonInfo(@RequestBody PersonInfoRequest request) {
		return personInfoService.getPersonInfo();
	}
	
	
	@PostMapping(value = "add_Person_Info")
	public PersonInfoResponse addPersonInfo(@RequestBody AddPersonInfoRequest request) {
		return personInfoService.addPersonInfo(request.getPersonInfoList());
		
	}
	
	@PostMapping(value = "get_person_info_by_id")
	public GetPersonInfoResponse getPersonInfoById(@RequestBody GetPersonInfoRequest request) {
		return personInfoService.getPersonInfoById(request.getId());
		
	}
	
	@PostMapping(value = "get_Person_Info_ByAgeGreaterThan")
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(@RequestBody PersonInfoRequest personInfoRequest) {
		return personInfoService.getPersonInfoByAgeGreaterThan(personInfoRequest);
	}
	
	@PostMapping(value = "get_Person_Info_ByAgeLessThanEqual")
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(@RequestBody PersonInfoRequest personInfoRequest) {
		return personInfoService.getPersonInfoByAgeLessThanEqual(personInfoRequest);
	}
	
	@PostMapping(value = "get_Person_InfoByAgeBetween")
	public PersonInfoResponse getPersonInfoByAgeBetween(@RequestBody PersonInfoRequest personInfoRequest) {
		return personInfoService.getPersonInfoByAgeBetween(personInfoRequest);
	}
	
	@PostMapping(value = "get_Person_Info_Containing")
	public GetPersonInfoResponse getPersonInfoContaining(@RequestBody PersonInfoRequest personInfoRequest) {
		return personInfoService.getPersonInfoContaining(personInfoRequest);
	}
	
	@PostMapping(value = "get_PersonInfo_ByAgeAndCityContaining")
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(@RequestBody PersonInfoRequest personInfoRequest) {
		return personInfoService.getPersonInfoByAgeAndCityContaining(personInfoRequest);
	}

}
