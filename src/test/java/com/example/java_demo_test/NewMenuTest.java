package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.NewMenu;
import com.example.java_demo_test.repository.NewMenuDao;

@SpringBootTest(classes = JavaDemoTestApplication.class)

public class NewMenuTest {
	
	@Autowired
	private NewMenuDao newMenuDao;
	
	@Test
	public void addDataTest() {
//		NewMenu num1 = new NewMenu("beef","紅燒",100);
		
	}
	
	

}
