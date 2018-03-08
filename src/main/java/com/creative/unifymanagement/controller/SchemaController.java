package com.creative.unifymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.creative.unifymanagement.business.SchemaBusiness;

@RestController
public class SchemaController {
	@Autowired
	SchemaBusiness business;
	
	@RequestMapping(value ="/schemas",method = RequestMethod.GET)
	public String handleGetAllSchema() {
		return business.getListSchema().toString();
	}

	@RequestMapping(value ="/schemas/reload",method = RequestMethod.GET)
	public String handleReloadSchema() {
		business.reloadSchema();
		return "success";
	}

}
