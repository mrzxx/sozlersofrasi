package com.sozlersofrasi.api.utilities;

public class Result {

    Integer statusCode;
	String message;
	
	public Result(Integer statusCode) {
		this.statusCode=statusCode;
	}
	
	public Result(Integer statusCode,String message) {
		this(statusCode);	
		this.message=message;
	}
	
	public Integer getStatusCode() {
		return this.statusCode;
	} 
	
	public String getMessage() {
		return this.message;
	}

}