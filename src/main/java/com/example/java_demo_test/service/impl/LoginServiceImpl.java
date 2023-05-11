package com.example.java_demo_test.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.naming.ContextNotEmptyException;

import org.aspectj.weaver.loadtime.Agent;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.hibernate.tuple.InDatabaseValueGenerationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService {

	String accountPattern = "\\w{3,8}";
	String pwdPattern = "^(?=.*[\\S^\\w])[\\S]{8,12}$";
	// 其他正則表達式寫法：
	// "[\\S]{8,16}" 不限定特殊符號至少有一個
	// "^(\\p{Punct}[\\S]{8,12}$" \p表示比對的字元具備某種特性, punct表示標點符號
	// "^(?=.*[\\S^\\w])[\\S]{8,12}$"
	@Autowired
	private LoginDao loginDao;

//	@Override
	/*
	 * 新增註冊資訊 使用者帳號、密碼、姓名、年齡、居住城市、註冊時間、帳號是否生效基本屬性
	 * 
	 * 1. 帳號長度 3~8 碼，不能有任何空白 2. 密碼長度 8 ~12 碼，至少要有一個特殊符號但不能有空白、Tab、定位、換行、換頁等符號 3.
	 * 帳號密碼姓名為必填 //
	 */
	public LoginResponse addLoginInfo(LoginRequest loginRequest) { // 單筆註冊資料
		// 如果直接丟login會使得外部可以直接變動active帳號生效的狀態,所以改用LoginRequest loginRequest的寫法
		String account = loginRequest.getAccount();
		String pwd = loginRequest.getPwd();
		String name = loginRequest.getName();

		if (!StringUtils.hasText(account) || !account.matches(accountPattern)) {
			return new LoginResponse("新增帳號錯誤", account);
		}
		if (!StringUtils.hasText(pwd) || !account.matches(pwdPattern)) {
			return new LoginResponse("新增密碼錯誤", null, pwd, null);
		}
		if (!StringUtils.hasText(name)) {
			return new LoginResponse("新增姓名錯誤", null, null, name);
		}

		// 判斷年齡 年齡預設為0時
		if (loginRequest.getAge() < 0) {
			return new LoginResponse("年齡不得為0");
		}

		// 帳號不得有重複, 如果是true就代表重複 因為資料庫有存在這筆帳號

		if (loginDao.existsById(account)) {
			return new LoginResponse("此帳號已重複");
		}
		// 假設使用者有打就丟進去資料庫中 記得要先去entity建建構方法 如果沒有的就會是預設null
		Login login = new Login(loginRequest.getAccount(), loginRequest.getPwd(), loginRequest.getName(),
				loginRequest.getAge(), loginRequest.getCity());

		loginDao.save(login);
		return new LoginResponse("註冊完成");
	}

//	@Override
	/*
	 * 新增註冊資訊 使用者帳號、密碼、姓名、年齡、居住城市、註冊時間、帳號是否生效基本屬性
	 * 
	 * 1. 帳號長度 3~8 碼，不能有任何空白 2. 密碼長度 8 ~12 碼，至少要有一個特殊符號但不能有空白、Tab、定位、換行、換頁等符號 3.
	 * 帳號密碼姓名為必填
	 */
//	public LoginResponse addLoginInfo(List<Login> login) { //待修改 因為以邏輯來說註冊時只會有一筆資料而非多筆
//
//		if (CollectionUtils.isEmpty(login)) {
//			return new LoginResponse("新增資訊錯誤");
//		}
//		List<String> accounts = new ArrayList<>();
//		for (Login item : login) {
//			String account = item.getAccount();
//			String pwd = item.getPwd();
//			String name = item.getName();
//
//			if (!StringUtils.hasText(account) || !account.matches(accountPattern)) {
//				return new LoginResponse("新增帳號錯誤", account);
//			} 
//			if (!StringUtils.hasText(pwd)|| !pwd.matches(pwdPattern)) {
//				return new LoginResponse("新增密碼錯誤", null, pwd, null);
//			}
//			if (!StringUtils.hasText(item.getName())) {
//				return new LoginResponse("新增姓名錯誤", null, null, name);
//			}
//			if (item.getAge() < 0) {
//				return new LoginResponse("年紀不得為0");
//			}
//			accounts.add(item.getAccount());
//		}
//		List<Login> resList = loginDao.findAllById(accounts); //改為exist
//		if (resList.size() == login.size()) {
//			return new LoginResponse("帳號已重複");
//		}
//		if (resList.size() > 0) {//移除
//			List<String> resAccount = new ArrayList<>();
//			for (Login item : resList) {
//				resAccount.add(item.getAccount());
//			}
//
//			List<Login> saveInfo = new ArrayList<>();
//			for (Login item : login) {
//				if (!resAccount.contains(item.getAccount())) {
//					saveInfo.add(item);
//				}
//			}
//			loginDao.saveAll(saveInfo);
//			return new LoginResponse("新增無重複資訊成功", saveInfo);
//
//		}
//		loginDao.saveAll(login);
//		return new LoginResponse("註冊完成", login);
//
//	}

	@Override
	/*
	 * 帳號生效 判斷帳號密碼是否正確 是否存在於資料庫 比對資料庫的帳號密碼是否正確
	 * 
	 */
	public LoginResponse accountActive(LoginRequest loginRequest) {
		String account = loginRequest.getAccount();
		String pwd = loginRequest.getPwd();

		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new LoginResponse("帳號或密碼輸入有誤");
		}
		// 到Dao定義JPA方法
		Login op = loginDao.findByAccountAndPwd(account, pwd);

		//不告訴使用者哪裡有錯, 新增判斷active 

		if (op == null) {
			return new LoginResponse("帳號或密碼錯誤");
		}
		
		//假如使用者帳號已生效過，跳出錯誤訊息
		if (op.isActive()) {
			return new LoginResponse("帳號已生效過");
		}
		
		op.setActive(true);

		loginDao.save(op);
		return new LoginResponse("帳號生效", op.getAccount());
	}

//		if (!op.isPresent()) {
//			return new LoginResponse("帳號不存在");
//		}
//		if (!op.get().getAccount().equals(account)) {
//			return new LoginResponse("帳號不正確");
//		}
//		if (!op.get().getPwd().equals(pwd)) {
//			return new LoginResponse("密碼不正確");
//		}

		

	@Override
	/*
	 * 登入 帳號密碼、帳號是否有生效 判斷帳號密碼沒打錯且有存在在資料庫 帳號狀態active
	 */
	public LoginResponse loginScuess(LoginRequest loginRequest) {
		String account = loginRequest.getAccount();
		String pwd = loginRequest.getPwd();

		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new LoginResponse("帳號或密碼輸入有誤");
		}
		Optional<Login> op = loginDao.findById(account);// 依entity的抓取request的帳號

		if (!op.isPresent()) {
			return new LoginResponse("帳號不存在");
		}
		if (!op.get().getAccount().equals(account)) {
			return new LoginResponse("帳號不正確");
		}
		if (!op.get().getPwd().equals(pwd)) {
			return new LoginResponse("密碼不正確");
		}

		if (op.get().isActive() == false) {
			return new LoginResponse("帳號尚未生效請驗證");
		}

		return new LoginResponse("登入成功");
	}

	@Override
	/*
	 * 居住在某個城市的使用者搜尋，Response 不包含密碼 要照年齡排序
	 */
	public LoginResponse getloginInfofindByCityContaining(LoginRequest loginRequest) {
//		findByCityAndPwdIsNullContainingNameOrderByAge
		String city = loginRequest.getCity();
		String name = loginRequest.getName();// revise

		List<Login> result = loginDao.findByCityAndNameContainingOrderByAge(city, name);
		List<Login> newResult = new ArrayList<>();// remove
		for (Login item : result) {
			Login a = new Login(item.getAccount(), null, item.getName(), item.getAge(), item.getCity(),
					item.getRegTime(), item.isActive());// 使用entity內建的建構方法
//			Login a = new Login(item.getAccount(),item.getName(),item.getAge(),item.getCity());
			newResult.add(a);// 將最終結果（密碼為null)存入a
		}
		return new LoginResponse("查詢成功", newResult);
	}

}
