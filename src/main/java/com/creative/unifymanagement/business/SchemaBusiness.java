package com.creative.unifymanagement.business;

import java.io.IOException;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class SchemaBusiness {
	public JSONArray getListSchema() {
		JSONArray result = new JSONArray();
		for(String id: JsonSchemaUtils.getSchemaAsObject().keySet()) {
			result.put(id);
		}
		return result;
	}
	public void reloadSchema() {
		try {
			JsonSchemaUtils.loadSchema();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
