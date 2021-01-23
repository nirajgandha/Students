package com.school.students.activity

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.school.students.R
import com.school.students.adapter.StudentListAdapter
import com.school.students.databinding.ActivitySelectStudentBinding
import com.school.students.interfaces.StudentSelectedListener
import com.school.students.model.GetStudentListResponse
import com.school.students.model.SelectStudentResponse
import com.school.students.model.Student
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Preference
import com.school.students.utils.Utils
import com.school.students.utils.saveStudentToPreference.saveStudentPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectStudentActivity : AppCompatActivity(), StudentSelectedListener {
    private var _binding: ActivitySelectStudentBinding ?= null
    private val binding get() = _binding!!
    private var preference: Preference ?= null
    private var student: Student ?= null
    private var studentSelectedListener: StudentSelectedListener = this
    private var phone = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = Preference(this)
        if (resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT){
            requestedOrientation = Configuration.ORIENTATION_PORTRAIT
        }
        _binding = ActivitySelectStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundleExtra = intent.getBundleExtra(getString(R.string.selectStudentBundle))!!
        phone = bundleExtra.getString(getString(R.string.phone),"")
        studentSelectedListener = this
        loadData(phone)
        binding.btnVerify.setOnClickListener {
            if (student == null){
                showError("Select the student to continue")
            } else {
                callSelectedStudent()
            }
        }
    }

    private fun callSelectedStudent() {
        Utils.showProgress(this)
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val loginApi: Call<SelectStudentResponse> = apiInterface.selectStudentApi(phone, student!!.id)
        loginApi.enqueue(object : Callback<SelectStudentResponse> {
            override fun onResponse(call: Call<SelectStudentResponse>, response: Response<SelectStudentResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null){
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.student_selected_successfully), true)){
                        val data = body.data
                        preference!!.setString(preference!!.loginId, phone)
                        saveStudentPreference(this@SelectStudentActivity, data[0])
                        val intent = Intent(this@SelectStudentActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        finish()
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<SelectStudentResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun loadData(phone: String) {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.studentRecycleView.layoutManager = linearLayoutManager
        val decorationItem : DividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation)
        binding.studentRecycleView.addItemDecoration(decorationItem)
        callGetStudentListApi(phone)
    }

    private fun callGetStudentListApi(phone: String) {
        Utils.showProgress(this)
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val loginApi: Call<GetStudentListResponse> = apiInterface.getStudentListApi(phone)
        loginApi.enqueue(object : Callback<GetStudentListResponse> {
            override fun onResponse(call: Call<GetStudentListResponse>, response: Response<GetStudentListResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null){
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.student_list_successful), true)){
                        val data = body.data
                        val studentListAdapter = StudentListAdapter(data, studentSelectedListener, this@SelectStudentActivity)
                        binding.studentRecycleView.adapter = studentListAdapter
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<GetStudentListResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    override fun onStudentSelected(student: Student) {
        this.student = student
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string, this).show()
    }
}