package com.creative.unifymanagement.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creative.unifymanagement.dao.GenericDao;
import com.creative.unifymanagement.dao.Object;

@Component
public class ObjectBusiness {

	@Autowired
	GenericDao dao;

	public Set<String> getAllKeys(String domain){
		return dao.getAllKeys(domain);
	}

	public void save(String domain, Object data) {
		validateObject(data, JsonSchemaUtils.getSchemaAsObject().get(domain));
		dao.save(domain, data);
	}

	public void deleteAll(String domain) {
		dao.deleteAll(domain);
	}
	public void delete(String domain, Object data) {
		dao.delete(domain, data.getId());
	}
	public Object get(String domain,String key) {
		try {
			Object result = dao.get(domain, key);
			Schema schema = JsonSchemaUtils.getSchemaAsObject().get(domain);
			return validateObject(result, schema);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object validateObject(Object result, Schema schema) {
		try {
			result.setError(false);
			schema.validate(result.getData());
		} catch (ValidationException ex) {
			result.setError(true);
			result.setErrorDetail(ex.toJSON());
		}
		return result;
	}

	public boolean exist(String domain,String key) {
		return dao.exist(domain, key);
	}

	public JSONArray getAll(String domain) {
		JSONArray result = new JSONArray();
		try {
			List<Object> temp = dao.getAll(domain);
			Schema schema = JsonSchemaUtils.getSchemaAsObject().get(domain);
			for(Object obj : temp) {
				validateObject(obj, schema);
				result.put(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
