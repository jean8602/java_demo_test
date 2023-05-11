package com.example.java_demo_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@RestController
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@PostMapping(value = "register")
	public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
		return registerService.register(registerRequest.getAccount(), registerRequest.getPwd());
	}

	@PostMapping(value = "active")
	public RegisterResponse active(@RequestBody RegisterRequest registerRequest) {
		return registerService.active(registerRequest.getAccount(), registerRequest.getPwd());
	}

	@PostMapping(value = "login")
	public RegisterResponse login(@RequestBody RegisterRequest registerRequest, HttpSession httpSession) {
		RegisterResponse res = registerService.login(registerRequest.getAccount(), registerRequest.getPwd());
		if (res.getMessage().equalsIgnoreCase("login successful")) {
			double random = Math.random() * 10000;
			int verifyCode = (int) Math.round(random);
			httpSession.setAttribute("verifyCode", verifyCode);
			httpSession.setAttribute("account", registerRequest.getAccount());
			httpSession.setAttribute("pwd", registerRequest.getPwd());
			httpSession.setMaxInactiveInterval(60);// 單位為秒
			res.setSessionId(httpSession.getId());
			res.setVerifyCode(verifyCode);
		}
		return res;
	}

	@PostMapping(value = "getRegTime")
	public RegisterResponse getRegTime(@RequestBody RegisterRequest registerRequest) {
		return registerService.getRegTime(registerRequest.getAccount(), registerRequest.getPwd());
	}

	@PostMapping(value = "getRegTime1")
	public RegisterResponse getRegTime1(@RequestBody RegisterRequest registerRequest, HttpSession httpSession) {
		String account = (String) httpSession.getAttribute("account");
		String pwd = (String) httpSession.getAttribute("pwd");
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("please login");
		}
		// 若值為null 不能轉型為int, 只能轉成Integer。未login過或時效過期才可能會產生null的情況
		Integer verifyCode = (Integer) httpSession.getAttribute("verifyCode");
		if (verifyCode == null || verifyCode != registerRequest.getVerifyCode()) {
			return new RegisterResponse("verify code error");
		}

		return registerService.getRegTime(account, pwd);
	}
	
	@PostMapping(value = "getRegTime2")
	public RegisterResponse getRegTime2(@RequestBody RegisterRequest registerRequest, HttpSession httpSession) {
		String account = (String) httpSession.getAttribute("account");
		String pwd = (String) httpSession.getAttribute("pwd");
		Integer verifyCode = (Integer) httpSession.getAttribute("verifyCode");
		return registerService.getRegTime(account, pwd);
	}

}
