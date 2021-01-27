package com.school.students.model;

import com.google.gson.annotations.SerializedName;

public class PaymentHistoryItem{

	@SerializedName("payment_detail")
	private String paymentDetail;

	@SerializedName("receipt_no")
	private String receiptNo;

	@SerializedName("class_id")
	private String classId;

	@SerializedName("payment_status")
	private String paymentStatus;

	@SerializedName("student_id")
	private String studentId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("payment_doc")
	private String paymentDoc;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("total_payment")
	private String totalPayment;

	@SerializedName("student_name")
	private String studentName;

	@SerializedName("payment_type")
	private String paymentType;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("check_bounced")
	private String checkBounced;

	@SerializedName("id")
	private String id;

	@SerializedName("class_name")
	private String className;

	@SerializedName("payment_date")
	private String paymentDate;

	public String getPaymentDetail(){
		return paymentDetail;
	}

	public String getReceiptNo(){
		return receiptNo;
	}

	public String getClassId(){
		return classId;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}

	public String getStudentId(){
		return studentId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getPaymentDoc(){
		return paymentDoc;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getTotalPayment(){
		return totalPayment;
	}

	public String getStudentName(){
		return studentName;
	}

	public String getPaymentType(){
		return paymentType;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getCheckBounced(){
		return checkBounced;
	}

	public String getId(){
		return id;
	}

	public String getClassName(){
		return className;
	}

	public String getPaymentDate(){
		return paymentDate;
	}

	@Override
 	public String toString(){
		return 
			"PaymentHistoryItem{" + 
			"payment_detail = '" + paymentDetail + '\'' + 
			",receipt_no = '" + receiptNo + '\'' + 
			",class_id = '" + classId + '\'' + 
			",payment_status = '" + paymentStatus + '\'' + 
			",student_id = '" + studentId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",payment_doc = '" + paymentDoc + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",total_payment = '" + totalPayment + '\'' + 
			",student_name = '" + studentName + '\'' + 
			",payment_type = '" + paymentType + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",check_bounced = '" + checkBounced + '\'' + 
			",id = '" + id + '\'' + 
			",class_name = '" + className + '\'' + 
			",payment_date = '" + paymentDate + '\'' + 
			"}";
		}
}