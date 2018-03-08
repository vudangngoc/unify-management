package com.creative.unifymanagement.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

public class JsonSchemaUtils {
	private static Map<String,Schema> schemaAsObject = new HashMap<>();
	static {
		try {
			loadSchema();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static File getSchemaFolder() {
		URL url = JsonSchemaUtils.class.getClassLoader().getResource("schemas/");
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			file = new File(url.getPath());
		} finally {
			return file;
		}
	}

	public static void loadSchema() throws IOException {
		schemaAsObject.clear();
		File folder  = getSchemaFolder();
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				FileInputStream inputStream = null;
				try {
					inputStream= new FileInputStream(listOfFiles[i]);
					String schemaString = IOUtils.toString(inputStream);
					Schema schemaObject = SchemaLoader.load(new JSONObject(schemaString));
					schemaAsObject.put(schemaObject.getId(), schemaObject);			
				}
				finally {
					if(inputStream != null)
						inputStream.close();
				}
			} 
		}
	}
	
	
	public static boolean isValidate(Schema schema,JSONObject data) {
		try {
			schema.validate(data);
		}catch(ValidationException ex) {
			return false;
		}
		return true;
	}
	public static Map<String, Schema> getSchemaAsObject() {
		return schemaAsObject;
	}

	public static boolean validate(JSONObject data) {
		// TODO Auto-generated method stub
		return false;
	}
}
