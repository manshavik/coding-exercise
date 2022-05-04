package com.willington.test.fundinvestment.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseBean<T> {

	private T result;
	private Date time;

	public ResponseBean(T result) {
		super();
		this.result = result;
		this.time = new Date();
	}
	
}
