package com.David.javaProject.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


public class ValidationError {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> errors = new ArrayList<>();
	
	private final String errorMessage;
	
	public ValidationError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void addValidationError(String error) {
		this.errors.add(error);
	}
	
	public List<String> getErrors(){
		return this.errors;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
