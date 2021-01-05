package com.school.students.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.school.students.R
import com.school.students.databinding.ActivityOtpBinding
import com.school.students.model.LoginResponse
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpActivity : AppCompatActivity() {

    private var _binding : ActivityOtpBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundleExtra = intent.getBundleExtra(getString(R.string.otpBundle))!!
        val phone: String = bundleExtra.getString(getString(R.string.phone),"")
        val otp = bundleExtra.getString(getString(R.string.otp), "")!!
        binding.edtOtp.text = Editable.Factory.getInstance().newEditable(otp)
        binding.btnVerify.setOnClickListener {
            callVerifyApi(phone, otp)
        }
    }

    private fun callVerifyApi(phone: String, otp: String) {
        Utils.showProgress(this)
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val loginApi: Call<LoginResponse> = apiInterface.verifyOtpApi(phone, otp)
        loginApi.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null){
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.otp_successful_message), true)){
                        val data = body.data
                        val bundle = Bundle()
                        bundle.putString(getString(R.string.phone), data.phone)
                        val intent = Intent(this@OtpActivity, SelectStudentActivity::class.java)
                        intent.putExtra(getString(R.string.selectStudentBundle), bundle)
                        startActivity(intent)
                        finish()
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string, this).show()
    }
}