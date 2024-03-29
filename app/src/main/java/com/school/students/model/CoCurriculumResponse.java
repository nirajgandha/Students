package com.school.students.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class CoCurriculumResponse{

	@SerializedName("data")
	private ArrayList<CoCurriculum> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<CoCurriculum> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"CoCurriculumResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}