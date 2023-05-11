package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.repository.NewMenu2Dao;



@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenu2Test {
	@Autowired
	private NewMenu2Dao newMenu2Dao;
	
	@Test
	public void addDataTest() {

		NewMenu2 num1 = new NewMenu2("beef","紅燒",100);
		NewMenu2 num2 = new NewMenu2("beef","滷",100);
		newMenu2Dao.save(num1);
		newMenu2Dao.save(num2);
	}

}
