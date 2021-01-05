package com.school.students.model;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class StudentListResponse {

	@SerializedName("data")
	private ArrayList<Student> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<Student> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"StudentListResponse{" +
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}