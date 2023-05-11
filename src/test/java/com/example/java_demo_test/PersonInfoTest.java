package com.example.java_demo_test;

import static org.mockito.ArgumentMatchers.intThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.repository.PersonInfoDao;
import com.example.java_demo_test.service.ifs.PersonInfoService;

@SpringBootTest(classes = JavaDemoTestApplication.class)

public class PersonInfoTest {
	
	@Autowired
	private PersonInfoDao personInfoDao;

	@Autowired
	private PersonInfoService personInfoService;

	@Test
	public void addPersonInfo() {
		int result = personInfoDao.updateNameById("A01", "jean", 12, "taipei");
		System.out.println(result);
		//回傳0 or 1 ->0 表示沒有修改, 1 表示有修改
	}
	
	@Test
	public void findByNameContainingOrCityContaining() {
		
		
	}
	
	

}
