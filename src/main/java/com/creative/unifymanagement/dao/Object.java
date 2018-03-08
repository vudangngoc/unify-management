package com.creative.unifymanagement.dao;

import java.util.UUID;

import org.json.JSONObject;

public class Object {
	private static final String HAS_ERROR = "hasError";
	private static final String ERROR_DETAIL = "errorDetail";
	public static String ID = "id";
	public Object() {
		this(UUID.randomUUID().toString().replace("-", ""));
	}
	public Object(String id) {
		data = getDefault();
		setId(id);
	}
	public void setId(String id) {
		if(data != null) data.put(ID, id);
		this.id = id;
	}
	protected String id;
	protected JSONObject data;
	public String getId() {
		String result = "";
		result = getData().getString(ID);
		if(!"".equals(result))
			return getData().getString(ID);
		else
			return this.id;
	}
	public JSONObject getData() {
		return data;
	}

	public String getProperty(String propertyName){
		if(data.has(propertyName))
			return data.getString(propertyName);
		else
			return "";
	}

	public boolean setData(JSONObject data) {
		if(this.valid(data)){
			this.data = data;
			return true;
		}else{
			return false;
		}
	}

	public boolean hasError() {
		if(getData().has(HAS_ERROR))
			return getData().getBoolean(HAS_ERROR);
		return false;
	}
	
	public void setError(boolean value) {
		getData().put(HAS_ERROR, value);
	}
	protected JSONObject getDefault() {
		JSONObject result = new JSONObject();
		return result;
	}
	@Override
	public String toString() {return getData().toString();}
	public  boolean valid(JSONObject object) {
		return true;
	}
	public void setErrorDetail(JSONObject error) {
		
		getData().put(ERROR_DETAIL, error);
	}
	
	public JSONObject getErrorDetail() {
		return getData().getJSONObject(ERROR_DETAIL);
	}
}
