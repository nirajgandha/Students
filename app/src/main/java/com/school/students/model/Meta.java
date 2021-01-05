package com.school.students.model;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("next")
	private String next;

	@SerializedName("code")
	private String code;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setNext(String next){
		this.next = next;
	}

	public String getNext(){
		return next;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}