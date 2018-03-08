package com.creative.unifymanagement.controller;

import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.creative.unifymanagement.business.ObjectBusiness;
import com.creative.unifymanagement.dao.Object;

@RestController
public class ObjectController {
	@Autowired
	ObjectBusiness objectBusiness;
	@RequestMapping(value ="/object/save",method = RequestMethod.POST)
	public String handleSave(String domain,@RequestBody String jsonData) {
		Object data = new Object();
		data.setData(new JSONObject(jsonData));
		objectBusiness.save(domain, data);
		return "success";
	}

	@RequestMapping(value ="/object/getAllKeys",method = RequestMethod.GET)
	public String handleGetAllKeys(String domain) {
		JSONArray result = new JSONArray();
		Set<String> keys = objectBusiness.getAllKeys(domain);
		for(String key : keys) {
			result.put(key);
		}
		return result.toString();
	}
	
	@RequestMapping(value ="/object/getAll",method = RequestMethod.GET)
	public String handleGetAll(String domain) {
		return objectBusiness.getAll(domain).toString();
	}
	
	@RequestMapping(value ="/object/get",method = RequestMethod.GET)
	public String handleGet(String domain, String id) {
		return objectBusiness.get(domain,id).toString();
	}
	
	@RequestMapping(value ="/object/exist",method = RequestMethod.GET)
	public String handleExist(String domain, String id) {
		return "{" + objectBusiness.exist(domain,id) + "}";
	}
	
	@RequestMapping(value ="/object/deleteAll",method = RequestMethod.PUT)
	public String handleDeleteAll(String domain) {
		objectBusiness.deleteAll(domain);
		return "success";
	}
	
	@RequestMapping(value ="/object/delete",method = RequestMethod.PUT)
	public String handleDeleteAll(String domain,@RequestBody String jsonData) {
		Object obj = new Object();
		obj.setData(new JSONObject(jsonData));
		objectBusiness.delete(domain,obj);
		return "success";
	}
}
