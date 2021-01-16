package com.school.students.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.school.students.R
import com.school.students.adapter.CoCurriculumAdapter
import com.school.students.databinding.FragmentCoCurriculumBinding
import com.school.students.model.CoCurriculumResponse
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Preference
import com.school.students.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CoCurriculumFragment : Fragment() {

    private var _binding : FragmentCoCurriculumBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = Preference(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCoCurriculumBinding.inflate(inflater)
        binding.backNavigation.setOnClickListener { requireActivity().onBackPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.coactivityRecyclerview.layoutManager = gridLayoutManager
        callCoCurriculumList()
    }

    private fun callCoCurriculumList() {
        Utils.showProgress(requireContext())
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val classId = preference!!.getString(preference!!.class_id, "")
        val loginApi: Call<CoCurriculumResponse> = apiInterface.getCoActivityListApi(classId)
        loginApi.enqueue(object : Callback<CoCurriculumResponse> {
            override fun onResponse(call: Call<CoCurriculumResponse>, response: Response<CoCurriculumResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null) {
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.activity_found_successful), true)) {
                       binding.coactivityRecyclerview.adapter = CoCurriculumAdapter(body.data, requireContext())
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<CoCurriculumResponse>, t: Throwable) {
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