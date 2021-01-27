package com.school.students.model;

import com.google.gson.annotations.SerializedName;

public class FeeHistoryItem{

	@SerializedName("student_name")
	private String studentName;

	@SerializedName("session_name")
	private String sessionName;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("student_fee_head")
	private String studentFeeHead;

	@SerializedName("class_id")
	private String classId;

	@SerializedName("student_fee_value")
	private String studentFeeValue;

	@SerializedName("student_id")
	private String studentId;

	@SerializedName("session_id")
	private String sessionId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("class_name")
	private String className;

	public String getStudentName(){
		return studentName;
	}

	public String getSessionName(){
		return sessionName;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getStudentFeeHead(){
		return studentFeeHead;
	}

	public String getClassId(){
		return classId;
	}

	public String getStudentFeeValue(){
		return studentFeeValue;
	}

	public String getStudentId(){
		return studentId;
	}

	public String getSessionId(){
		return sessionId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getId(){
		return id;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getClassName(){
		return className;
	}

	@Override
 	public String toString(){
		return 
			"FeeHistoryItem{" + 
			"student_name = '" + studentName + '\'' + 
			",session_name = '" + sessionName + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",student_fee_head = '" + studentFeeHead + '\'' + 
			",class_id = '" + classId + '\'' + 
			",student_fee_value = '" + studentFeeValue + '\'' + 
			",student_id = '" + studentId + '\'' + 
			",session_id = '" + sessionId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",class_name = '" + className + '\'' + 
			"}";
		}
}