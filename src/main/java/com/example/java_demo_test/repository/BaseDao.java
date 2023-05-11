package com.example.java_demo_test.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext // JPA專有的註釋
	private EntityManager entityManager;

//<EntityType>泛型寫法
//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
//		Query query = entityManager.createQuery(sql, clazz);
//		for (Entry<String, Object> item : params.entrySet()) {
//			query.setParameter(item.getKey(), item.getValue());
//		}
//		for (Parameter p : query.getParameters()) {
//			query.setParameter(p, params.get(p.getName()));
//		} //這個寫法會比較沒那麼直覺
//		return query.getResultList();
		return doQuery(sql, params, clazz, -1);
	}

	// limitsize限制回傳筆數
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitsize) {
//		Query query = entityManager.createQuery(sql, clazz);
//		if (!CollectionUtils.isEmpty(params)) {
//			for (Entry<String, Object> item : params.entrySet()) {
//				query.setParameter(item.getKey(), item.getValue());
//			}
//		}
//		if (limitsize > 0) {
//			query.setMaxResults(limitsize);
//		}
		return doQuery(sql, params, clazz, limitsize, -1);
	}

	// limitsize限制回傳筆數
	// startposition每頁起始位置
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitsize, int startPosition) {
		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}

		if (limitsize > 0) {
			query.setMaxResults(limitsize);
		}

		if (startPosition >= 0) {
			query.setFirstResult(startPosition);
		}
		return query.getResultList();
	}

	protected int doUpdate(String sql, Map<String, Object> params) {
		Query query = entityManager.createQuery(sql);
		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doNativeQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitsize, int startPosition) {
		Query query = entityManager.createNativeQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}

		if (limitsize > 0) {
			query.setMaxResults(limitsize);
		}

		if (startPosition >= 0) {
			query.setFirstResult(startPosition);
		}
		return query.getResultList();
	}
	
}
