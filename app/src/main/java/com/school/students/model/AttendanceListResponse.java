package com.school.students.model;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class AttendanceListResponse{

	@SerializedName("data")
	private ArrayList<Attendance> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<Attendance> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"AttendanceListResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}