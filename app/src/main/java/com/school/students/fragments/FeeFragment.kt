package com.school.students.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.school.students.R
import com.school.students.activity.MainActivity
import com.school.students.adapter.FeeHistoryAdapter
import com.school.students.adapter.PaymentHistoryAdapter
import com.school.students.databinding.FragmentFeeBinding
import com.school.students.model.*
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Preference
import com.school.students.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class FeeFragment : Fragment() {

    var _binding : FragmentFeeBinding? = null
    val binding get() = _binding!!
    var preference: Preference ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentFeeBinding.inflate(inflater)
        binding.imgSettings.setOnClickListener { (requireActivity() as MainActivity).startSettingsActivity() }
        binding.backNavigation.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preference = Preference(requireContext())
        callGetFeeListApi()
    }

    private fun segregateList(data: FeeData){
        binding.totalAmount.text = resources.getString(R.string.payment_string, data.totalAmount.toString())
        binding.totalDiscount.text = resources.getString(R.string.payment_string, data.totalDiscount.toString())
        binding.totalDuePayment.text = resources.getString(R.string.payment_string, data.totalDuePayment.toString())
        binding.totalPaid.text = resources.getString(R.string.payment_string, data.totalPaid.toString())
        if (data.feeHistory.isNotEmpty()){
            val feeAdapterItemObjectArrayList : ArrayList<FeeAdapterItemObject> = ArrayList()
            var classId = data.feeHistory[0].classId
            var feeitemArrayList: ArrayList<FeeHistoryItem> = ArrayList()
            for (dataItem in data.feeHistory){
                if (dataItem.classId == classId) {
                    feeitemArrayList.add(dataItem)
                } else {
                    feeAdapterItemObjectArrayList.add(FeeAdapterItemObject(classId, feeitemArrayList))
                    classId = dataItem.classId
                    feeitemArrayList = ArrayList()
                    feeitemArrayList.add(dataItem)
                }
            }
            feeAdapterItemObjectArrayList.add(FeeAdapterItemObject(classId, feeitemArrayList))
            binding.feeHistoryRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.feeHistoryRecyclerview.adapter = FeeHistoryAdapter(feeAdapterItemObjectArrayList, requireContext())
            for (feeAdapterObject in feeAdapterItemObjectArrayList) {
                Log.d("niraj", "Class Id: ${feeAdapterObject.class_id}")
                for (ad in feeAdapterObject.feeHistoryItemArrayList){
                    Log.d("niraj","\t${ad.studentFeeHead} ${ad.studentFeeValue}")
                }
            }
        }
        if (data.paymentHistory.isNotEmpty()) {
            binding.payRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.payRecyclerview.adapter = PaymentHistoryAdapter(data.paymentHistory, requireContext())
        } else {
            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.feeCl)
            constraintSet.connect(binding.feeHistoryRecyclerview.id, ConstraintSet.BOTTOM, binding.feeCl.id, ConstraintSet.BOTTOM)
            binding.feeCl.setConstraintSet(constraintSet)
            binding.payHistory.visibility = View.GONE
            binding.payRecyclerview.visibility = View.GONE
        }
    }

    private fun callGetFeeListApi() {
        Utils.showProgress(requireContext())
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val studentId = preference!!.getString(preference!!.ID, "")
        apiInterface.getFeeListApi(studentId).enqueue(object : Callback<FeeResponse> {
            override fun onResponse(call: Call<FeeResponse>, response: Response<FeeResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null) {
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.fee_found_successful), true)) {
                        segregateList(body.feeData)
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<FeeResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string, requireContext()).show()
    }
}