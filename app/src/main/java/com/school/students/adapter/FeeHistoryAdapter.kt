package com.school.students.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.school.students.R
import com.school.students.databinding.FeeRecyclerViewItemBinding
import com.school.students.databinding.FeeTableRowLayoutBinding
import com.school.students.databinding.HomeWorkRecyclerViewItemBinding
import com.school.students.model.FeeAdapterItemObject
import com.school.students.model.Homework
import java.util.*


class FeeHistoryAdapter(private var feeAdapterItemObjectArrayList: ArrayList<FeeAdapterItemObject>, private val context: Context) : RecyclerView.Adapter<FeeHistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = FeeRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(feeAdapterItemObjectArrayList[position]){
                if (position == feeAdapterItemObjectArrayList.size - 1){
                    (feeRecyclerViewItemBinding.root.layoutParams as RecyclerView.LayoutParams).bottomMargin = 100
                }
                feeRecyclerViewItemBinding.classNumber.text = context.resources.getString(R.string.str_class_section, "${feeHistoryItemArrayList[0].className} ${feeHistoryItemArrayList[0].sessionName}")
                for (item in feeHistoryItemArrayList) {
                    val tableRowLayoutBinding = FeeTableRowLayoutBinding.inflate(LayoutInflater.from(context))
                    if (item.studentFeeValue.contains("-")){
                        tableRowLayoutBinding.studentFeeHeader.setTextColor(ContextCompat.getColor(context, R.color.red))
                        tableRowLayoutBinding.studentFeeValue.setTextColor(ContextCompat.getColor(context, R.color.red))
                        tableRowLayoutBinding.studentFeeValue.text = context.getString(R.string.payment_string, item.studentFeeValue.substring(1))
                    } else {
                        tableRowLayoutBinding.studentFeeValue.text = context.getString(R.string.payment_string, item.studentFeeValue)
                    }
                    tableRowLayoutBinding.studentFeeHeader.text = item.studentFeeHead
                    feeRecyclerViewItemBinding.tableview.addView(tableRowLayoutBinding.root)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return feeAdapterItemObjectArrayList.size
    }

    fun refreshList(feeAdapterItemObjectArrayList: ArrayList<FeeAdapterItemObject>) {
        this.feeAdapterItemObjectArrayList = feeAdapterItemObjectArrayList
        notifyDataSetChanged()
    }

    class ViewHolder(val feeRecyclerViewItemBinding: FeeRecyclerViewItemBinding) : RecyclerView.ViewHolder(feeRecyclerViewItemBinding.root)

}