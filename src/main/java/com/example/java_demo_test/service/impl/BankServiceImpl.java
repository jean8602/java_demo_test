package com.example.java_demo_test.service.impl;

import java.security.PrivateKey;
import java.util.Optional;

import javax.management.loading.PrivateClassLoader;

import org.apache.coyote.http11.filters.VoidInputFilter;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable.Op;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@Service
public class BankServiceImpl implements BankService {

	String pattern = "\\w{3,8}";
	String pwdPattern = "\\S{8,16}";

	@Autowired
	private BankDao bankDao;

	@Override
	public void addInfo(Bank bank) {

//		檢查帳號
		checkAccount(bank);

		// 檢查密碼
		checkPwd(bank);

		// 檢查餘額 不能為負數
		int amount = bank.getAmount();
		if (amount < 0) {
			System.out.println("Amount is error!!");
			return;
		}
		// 新增資料到bank這張表 有存在才會update新增
		bankDao.save(bank);

	}

	private void checkAccount(Bank bank) {
		if (bank == null) {
			System.out.println("Bank is null!!");
			return;
		}
		String account = bank.getAccount();
		// 帳號判斷條件 1. 帳號長度3-8 2. 檢查帳號是否有空白或null 3.帳號不能有特殊符號~!@#$%^&*()
		if (account == null) {
			System.out.println("Account is null!!");
			return;
		}
		if (!account.matches(pattern)) {
			System.out.println("Account is error!!");
			return;
		}
	}

	private void checkPwd(Bank bank) {
		// 密碼條件 1. 非null 2.不能有空白 3. 長度8~16 4. 要包含一些特殊字元
		String pwd = bank.getPwd();
		if (pwd == null || !pwd.matches(pwdPattern)) {
			System.out.println("Password is error!!");
			return;
		}
	}

	@Override
	public Bank getAmountById(String Id) {
		if (!StringUtils.hasText(Id)) { // 使用這個類別可以同時判斷null與blank
			return new Bank();
		}
		Optional<Bank> op = bankDao.findById(Id);

		// 3. 修正後的寫法
//		Bank bank = op.orElse(new Bank());
//		return bank;

		// 4. 最終寫法 把3合併
		return op.orElse(new Bank());

		// 2. 正面寫法
//		if (op.isPresent()) {
////			Bank bank = op.get();
////			return bank;
//			return op.get();
//		} else {
////			Bank bank = new Bank();
////			return bank;
//			return new Bank();
//		}

		// 1. 反面寫法 等於3 慢慢修正成最終結果

//		if (!op.isPresent()) { 
//			return new Bank();
//		}
//		return op.get();
	}

	@Override // 存款
	public BankResponse deposit(Bank bank) {
//		if (bank == null) {
//			return new Bank();
//		}
//		if (StringUtils.hasText(bank.getAccount())) {
//			return new Bank();
//		}
//
//		if (StringUtils.hasText(bank.getPwd())) {
//			return new Bank();
//		}
//
//		if (bank.getAmount() <= 0) {
//			return new Bank();
//		}

		// ============================合併上述
		if (bank == null || !StringUtils.hasText(bank.getAccount()) || !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(),"帳號或密碼錯誤");
		}

//		Optional<Bank> op = bankDao.findById(bank.getAccount()); 
//		if (!op.isPresent()) {
//			return new Bank();
//		}
//		Bank resBank = op.get();

		Bank resBank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());

		if (resBank == null) {
			return new BankResponse(new Bank(),"資料不存在!!");
		}
		int oldAmount = resBank.getAmount();
		int depositAmount = bank.getAmount();
		int newAmount = oldAmount + depositAmount;
		resBank.setAmount(newAmount); // 設定存款後的金額
//		Bank newBank = bankDao.save(resBank);
//		return newBank;

		return new BankResponse(bankDao.save(resBank),"存款成功!!");
	}

	@Override // 提款
	public BankResponse withdraw(Bank bank) {
		if (bank == null || !StringUtils.hasText(bank.getAccount()) || !StringUtils.hasText(bank.getPwd())
				|| bank.getAmount() <= 0) {
			return new BankResponse(new Bank(),"帳號或密碼錯誤");
		}
		Bank resBank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());

		if (resBank == null) {
			return new BankResponse(new Bank(),"資料不存在!!");
		}

		int oldAmount = resBank.getAmount();
		int withdrawAmount = bank.getAmount();
		int newAmount = oldAmount - withdrawAmount;
		if (oldAmount < withdrawAmount) {
			return new BankResponse(new Bank(),"餘額不足!!");
		}
		resBank.setAmount(newAmount);
		
		return new BankResponse(bankDao.save(resBank),"提款成功!!");
	}

}
