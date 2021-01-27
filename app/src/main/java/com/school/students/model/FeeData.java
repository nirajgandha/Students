package com.school.students.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class FeeData {

	@SerializedName("total_due_payment")
	private int totalDuePayment;

	@SerializedName("PaymentHistory")
	private ArrayList<PaymentHistoryItem> paymentHistory;

	@SerializedName("total_discount")
	private int totalDiscount;

	@SerializedName("total_amount")
	private int totalAmount;

	@SerializedName("total_paid")
	private int totalPaid;

	@SerializedName("FeeHistory")
	private ArrayList<FeeHistoryItem> feeHistory;

	public int getTotalDuePayment(){
		return totalDuePayment;
	}

	public ArrayList<PaymentHistoryItem> getPaymentHistory(){
		return paymentHistory;
	}

	public int getTotalDiscount(){
		return totalDiscount;
	}

	public int getTotalAmount(){
		return totalAmount;
	}

	public int getTotalPaid(){
		return totalPaid;
	}

	public ArrayList<FeeHistoryItem> getFeeHistory(){
		return feeHistory;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"total_due_payment = '" + totalDuePayment + '\'' + 
			",paymentHistory = '" + paymentHistory + '\'' + 
			",total_discount = '" + totalDiscount + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",total_paid = '" + totalPaid + '\'' + 
			",feeHistory = '" + feeHistory + '\'' + 
			"}";
		}
}