package com.school.students.model;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("code")
	private String code;

	@SerializedName("phone")
	private String phone;

	@SerializedName("logged_in")
	private String loggedIn;

	@SerializedName("device_token")
	private String deviceToken;

	@SerializedName("otp")
	private String otp;

	@SerializedName("device_type")
	private String deviceType;

	@SerializedName("id")
	private String id;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setLoggedIn(String loggedIn){
		this.loggedIn = loggedIn;
	}

	public String getLoggedIn(){
		return loggedIn;
	}

	public void setDeviceToken(String deviceToken){
		this.deviceToken = deviceToken;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
	}

	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}