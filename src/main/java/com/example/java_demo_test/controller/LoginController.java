package com.example.java_demo_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
//	
//	@PostMapping(value = "add_Login_Info")
//	public LoginResponse addLoginInfo(@RequestBody LoginRequest loginrequest) {
//		return loginService.addLoginInfo(loginrequest.getLogin());
//		
//	}
	
	@PostMapping(value = "add_Login_Info")
	public LoginResponse addLoginInfo(@RequestBody LoginRequest loginrequest) {
		return loginService.addLoginInfo(loginrequest);
		
	}
	
	@PostMapping(value = "account_Active")
	public LoginResponse accountActive(@RequestBody LoginRequest loginrequest) {
		return loginService.accountActive(loginrequest);
		
	}
	
	@PostMapping(value = "login_Scuess")
	public LoginResponse loginScuess(@RequestBody LoginRequest loginrequest) {
		return loginService.loginScuess(loginrequest);
		
	}
	
	@PostMapping(value = "getloginInfo_findByCityContaining")
	public LoginResponse getloginInfofindByCityContaining(@RequestBody LoginRequest loginrequest) {
		return loginService.getloginInfofindByCityContaining(loginrequest);
		
	}
	
	
}
