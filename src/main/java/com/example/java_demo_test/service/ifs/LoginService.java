package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {
	
//	public LoginResponse addLoginInfo(List<Login> login);
	
//	public LoginResponse addLoginInfo(Login login);
	
	public LoginResponse addLoginInfo(LoginRequest loginRequest);
	
	public LoginResponse accountActive(LoginRequest loginRequest);
	
	public LoginResponse loginScuess(LoginRequest loginRequest);
	
	public LoginResponse getloginInfofindByCityContaining(LoginRequest loginRequest);

}
