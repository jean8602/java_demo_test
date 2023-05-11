package com.example.java_demo_test.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoDaoImpl extends BaseDao {

	public List<PersonInfo> doQueryByAge(int age) {
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :age");// age是entity的名字
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		return doQuery(sb.toString(), params, PersonInfo.class);

	}

	public List<PersonInfo> doQueryByAge(int age, int limitsize) {
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :age");// age是entity的名字
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limitsize);

	}

	public List<PersonInfo> doQueryByAge(int age, int limitsize, int startPosition) {
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :age");// age是entity的名字
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		return doQuery(sb.toString(), params, PersonInfo.class, limitsize, startPosition);

	}

	// 目前都需要透過entity query
	public int doUpdateAgeByName(int age, String name) {
		StringBuffer sb = new StringBuffer();
		sb.append("update PersonInfo set age = :age whrere name = :name");
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		params.put("name", name);
		return doUpdate(sb.toString(), params);

	}
}
