             package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.PersonInfoDao;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoRequest;
import com.example.java_demo_test.vo.PersonInfoResponse;

//先加＠service讓sprint boot託管 找到實作內容
@Service

public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
		// 防呆：檢查參數
//		1. list若為null會產生nullPointerException -> 2. 到PersonInfoResponse頁面生成預設空的＆有參數之建構方法（using field&superclass)
		if (CollectionUtils.isEmpty(personInfoList)) { // 原本用personInfoList==null,但替換此寫法可以判斷null & 是否為空empty
			return new PersonInfoResponse("新增資訊錯誤");
		}
		// 檢查List內的每一項資訊（personInfo各四個參數）使用for each遍歷list裡面的值、思考如何下一步判斷
		// hasText&hasLength的差別在於多判斷全空白字串

		List<String> ids = new ArrayList<>();// 從外部輸入要存放id的list
		for (PersonInfo item : personInfoList) {
			// 檢查ID,檢查name,檢查age,檢查city （單純字串檢查）
			if (!StringUtils.hasText(item.getId()) || !StringUtils.hasText(item.getName())
					|| !StringUtils.hasText(item.getCity()) || item.getAge() < 0) {
				return new PersonInfoResponse("新增資訊錯誤");
			}
			// 目的：搜集ＩＤ 為蒐集Id,先在迴圈外面建立String List 名稱為ids, 日後用來判斷id是否有重複
			ids.add(item.getId());
		}
		// 檢查要新增的ids是否存在 是否有重複 在資料庫撈
		List<PersonInfo> res = personInfoDao.findAllById(ids);// 和資料庫比對後有重複的id
		if (res.size() == personInfoList.size()) {
			return new PersonInfoResponse("要新增的資料已存在");// 要新增資料全部重複
		}
		if (res.size() > 0) { //>0的話代表搜尋到東西 即是資料有重複的意思
//			return new PersonInfoResponse("新增資訊已存在");
			// 只存不重複的資料
			// 把不重複的資料存入 重複的資料剔除
			List<String> resId = new ArrayList<>();// 放45行已經跟資料庫重複的id
			for (PersonInfo item : res) { // 先拿完id 再把他加入list
				resId.add(item.getId());
			}
			List<PersonInfo> saveInfo = new ArrayList<>(); // 新建一個list用來存放為重複的資料
			for (PersonInfo item : personInfoList) { // 遍歷外部personInfoList 那四個屬性的資料
				if (!resId.contains(item.getId())) { // 比對resId&外部personList是否有包含
					saveInfo.add(item);
				}
			}
			personInfoDao.saveAll(saveInfo);
			return new PersonInfoResponse("新增無重複資訊成功", saveInfo);
		}

		personInfoDao.saveAll(personInfoList);// 儲存多筆資料
		return new PersonInfoResponse("新增資訊成功", personInfoList);
	
	}

	@Override

	public PersonInfoResponse getPersonInfo() {
		// 取出所有資料庫裡的個人資料 因為是多筆資料所以用list
		List<PersonInfo> op = personInfoDao.findAll();
		return new PersonInfoResponse(op);
	}

	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
//		1.防呆檢查 不可為null 空白 空字串 
		if (!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse("id不得為空");
		}
//		從資料庫撈出對應的id資料
		Optional<PersonInfo> op = personInfoDao.findById(id);
		if (!op.isPresent()) {
			return new GetPersonInfoResponse("資料不存在");
		}
		return new GetPersonInfoResponse(op.get(), "成功");
	}

	@Override

	// 找出年紀大於輸入條件的所有個人資訊
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(PersonInfoRequest personInfoRequest) {
		// 先叫出資料庫裡面的資料比對
		int age = personInfoRequest.getAge();
		if (age < 0) {
			return new GetPersonInfoResponse("年紀錯誤");
		}
		List<PersonInfo> op = personInfoDao.findByAgeGreaterThan(age);
//		List<PersonInfo> op = personInfoDao.findAll();
//		List<String> idList = new ArrayList<>();
//		for (PersonInfo item : op) {// 假如現在資料庫撈出14,20 現在輸入15
//			if (item.getAge() > age) {
//				idList.add(item.getId());
//			}
//			
//		}
//		List<PersonInfo> result = personInfoDao.findAllById(idList);
		return new GetPersonInfoResponse("成功", op);
	}

//	找出年紀小於輸入條件1 或大於輸入條件2的所有個人資訊 年齡從小到大排列
	
	
	
	@Override // #5 找出年紀小於等於輸入條件的所有個人資訊
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(PersonInfoRequest personInfoRequest) {
		int age = personInfoRequest.getAge();
		if (age < 0) {
			return new GetPersonInfoResponse("年紀錯誤");
		}
		List<PersonInfo> op = personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		return new GetPersonInfoResponse("成功", op);
	}

	@Override // 找到年紀介於兩個數字（有包含）之間的資訊,只取前三筆,年齡由大到小排序
	public PersonInfoResponse getPersonInfoByAgeBetween(PersonInfoRequest personInfoRequest) {
		int age = personInfoRequest.getAge();
		int age2 = personInfoRequest.getAge2();
		if (age < 0 || age2 < 0) {
			return new PersonInfoResponse("年紀錯誤");
		}

		List<PersonInfo> op = personInfoDao.findTop3ByAgeBetweenOrderByAgeDesc(age, age2);
		return new PersonInfoResponse("成功", op);
	}

	@Override
	//取得 city 包含某個特定字的所有個人資訊
	public GetPersonInfoResponse getPersonInfoContaining(PersonInfoRequest personInfoRequest) {
		String keyword = personInfoRequest.getKeyword();
		if (!StringUtils.hasText(keyword)) {
			return new GetPersonInfoResponse("輸入資料有錯");
		}
		List<PersonInfo> op = personInfoDao.findByCityContaining(keyword);
		return new GetPersonInfoResponse("成功", op);
	}

	@Override
	//找出年紀大於輸入條件以及city 包含某個特定字的所有人資訊
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(PersonInfoRequest personInfoRequest) {
		int age = personInfoRequest.getAge();
		String keyword = personInfoRequest.getKeyword();
		List<PersonInfo> op = personInfoDao.findByAgeGreaterThanAndCityContainingOrderByAgeDesc(age,keyword);
		return new GetPersonInfoResponse("成功", op);
	}

}
