package com.example.java_demo_test.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> {

	public List<PersonInfo> findByAgeGreaterThan(int age);

	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);

	public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int age, int age2);

	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int age, int age2);

	public List<PersonInfo> findByCityContaining(String keyword);

	public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int age, String keyword);

	public List<PersonInfo> doQueryByAge(int age);

	public List<PersonInfo> doQueryByAge(int age, int limitsize);

	public List<PersonInfo> doQueryByAge(int age, int limitsize, int startPosition);
	
	public List<PersonInfo> findByNameContainingOrCityContaining(String name, String city);
	

	@Transactional
	public int doUpdateAgeByName(int age, String name);

	@Transactional
	@Modifying
	@Query("update PersonInfo p set p.id = :newId, p.name=:newName, p.age=:newAge, p.city=:newCity where p.id =:newId")
	// 根據要修改的欄位寫上就可以 不必寫出所有的資訊
	public int updateNameById(@Param("newId") String inputId, @Param("newName") String inputName,
			@Param("newAge") int inputAge, @Param("newCity") String inputCity);
	
	
	//兩個參數都沒值-船全部結果
	//兩個參數都沒值 沒資料
	//若其中一個有值 則返回符合條件的結果
	//若兩個都有值返回符合條件的結果
	
	//regexp 冒號後面都是自己取名 param後面接取名 後方屬性名稱也都是自己取名的
	@Transactional
	@Modifying
	@Query(value = "select * from PersonInfo p where p.name regexp :regexpName or p.city regexp :regexCity",nativeQuery=true)
	public List<PersonInfo> searchNameOrCity(@Param("regexpName") String inputName, @Param("regexCity") String inputCity);
}