package com.creative.unifymanagement.dao;

import java.util.List;
import java.util.Set;

public interface GenericDao {

	void save(String domain, Object data);

	Object get(String domain,String key) throws Exception;

	void delete(String domain,String key);

	Set<String> getAllKeys(String domain);

	boolean exist(String domain,String key);

	List<Object> getAll(String domain) throws Exception;

	boolean deleteAll(String domain);
}