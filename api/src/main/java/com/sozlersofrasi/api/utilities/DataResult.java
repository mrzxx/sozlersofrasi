package com.sozlersofrasi.api.utilities;

public class DataResult<T> extends Result {
    private T data;

    public DataResult(T data,Integer statusCode, String message) {
		super(statusCode, message);
		this.data=data;
	}
	
	public DataResult(T data,Integer statusCode) {
		super(statusCode);
		this.data=data;
	}
	
	public T getData() {
		return this.data;
	}
}
