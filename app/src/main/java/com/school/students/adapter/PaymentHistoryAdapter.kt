package com.school.students.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.school.students.R
import com.school.students.databinding.PaymentRecyclerViewItemBinding
import com.school.students.model.PaymentHistoryItem
import java.util.*


class PaymentHistoryAdapter(private var paymentHistoryItemArrayList: ArrayList<PaymentHistoryItem>, private val context: Context) : RecyclerView.Adapter<PaymentHistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = PaymentRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(paymentHistoryItemArrayList[position]){
                if (position == paymentHistoryItemArrayList.size -1) {
                    (paymentHistoryItemBinding.root.layoutParams as RecyclerView.LayoutParams).bottomMargin = 100
                }
                paymentHistoryItemBinding.classNumber.text = context.getString(R.string.class_s, className)
                paymentHistoryItemBinding.date.text = paymentDate
                paymentHistoryItemBinding.payment.text = context.getString(R.string.payment_string, totalPayment)
                paymentHistoryItemBinding.paymentType.text = paymentType
            }
        }
    }

    override fun getItemCount(): Int {
        return paymentHistoryItemArrayList.size
    }

    fun refreshList(paymentHistoryItemArrayList: ArrayList<PaymentHistoryItem>) {
        this.paymentHistoryItemArrayList = paymentHistoryItemArrayList
        notifyDataSetChanged()
    }

    class ViewHolder(val paymentHistoryItemBinding: PaymentRecyclerViewItemBinding) : RecyclerView.ViewHolder(paymentHistoryItemBinding.root)

}