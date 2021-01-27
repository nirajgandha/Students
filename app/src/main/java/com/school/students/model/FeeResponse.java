package com.school.students.model;

import com.google.gson.annotations.SerializedName;

public class FeeResponse{

	@SerializedName("data")
	private FeeData feeData;

	@SerializedName("meta")
	private Meta meta;

	public FeeData getFeeData(){
		return feeData;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"FeeResponse{" + 
			"data = '" + feeData + '\'' +
			",meta = '" + meta + '\'' + 
			"}";
		}
}