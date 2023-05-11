package com.example.java_demo_test.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.repository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.LoginResponse;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDao registerDao;

	@Override
	// 單筆註冊功能
	public RegisterResponse register(String account, String pwd) {
		String accountPattern = "\\w{3,8}";
//		String pwdPattern = "^(?=.*[\\S^\\w])[\\S]{8,12}$";

		if (!StringUtils.hasText(account) || !account.matches(accountPattern)) {
			return new RegisterResponse("新增帳號錯誤");
		}

		if (!StringUtils.hasText(pwd) /* || !account.matches(pwdPattern) */) {
			return new RegisterResponse("新增密碼錯誤");
		}

		if (registerDao.existsById(account)) {
			return new RegisterResponse("帳號重複");
		}

		// 將新增之帳號和密碼存入資料庫中
		Register resRegister = new Register(account, pwd);
		Register op = registerDao.save(resRegister);

		return new RegisterResponse("新增帳號成功");// 是否要印出結果
	}

	@Override
	// 帳號生效
	public RegisterResponse active(String account, String pwd) {
		// 先確認帳號是否有存在
		if (!registerDao.existsById(account)) {
			return new RegisterResponse("帳號不存在");
		}
//		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
//			return new RegisterResponse("帳號密碼有誤");
//		}
		// 確認是否已生效過
		Register result = registerDao.findByAccountAndPwd(account, pwd);
		if (!result.isActive() == false) {
			return new RegisterResponse("帳號尚未生效請驗證");
		}
		result.setActive(true);
		registerDao.save(result);
		return new RegisterResponse("生效成功");

	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		// 登入 帳號密碼輸入是否錯誤 是否有存在於資料庫中 是否已生效
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("帳號或密碼有誤");
		}

		Register op = registerDao.findByAccountAndPwdAndActive(account, pwd, true);

		if (op == null) {
			return new RegisterResponse("帳號或密碼錯誤或帳號未生效");
		}

		return new RegisterResponse("登入成功");
	}

	@Override
	public RegisterResponse getRegTime(String account, String pwd) {
		// 取得註冊時間前 要先確定有註冊成功 帳號密碼皆要正確

		Register result = registerDao.findByAccountAndPwd(account, pwd);
		if (result == null) {
			return new RegisterResponse("帳號或密碼錯誤");
		}

		return new RegisterResponse(result.getReg_time());
	}

	@Override
	public RegisterResponse getRegTime2(RegisterRequest registerRequest, String account, String pwd,
			Integer verifyCode) {
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("please login");
		}

		if (verifyCode == null || verifyCode != registerRequest.getVerifyCode()) {
			return new RegisterResponse("verify code error");
		}

		Register op = registerDao.findByAccountAndPwdAndActive(account, pwd, true);

		if (op == null) {
			return new RegisterResponse("登入失敗或未激活");
		}
		return new RegisterResponse(op.getReg_time());

	}

}
