package com.school.students.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.school.students.R
import com.school.students.adapter.NoticeAdapter
import com.school.students.databinding.FragmentNoticeBinding
import com.school.students.databinding.HomeworkDialogLayoutBinding
import com.school.students.databinding.NoticeDialogLayoutBinding
import com.school.students.interfaces.NoticeClickListener
import com.school.students.model.Notice
import com.school.students.model.NoticeResponse
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Preference
import com.school.students.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NoticeFragment : Fragment(), NoticeClickListener {

    private var _binding : FragmentNoticeBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private val noticeClickListener: NoticeClickListener = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = Preference(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentNoticeBinding.inflate(inflater)
        binding.backNavigation.setOnClickListener { requireActivity().onBackPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.noticeRecyclerview.layoutManager = linearLayoutManager
        callGetNoticeList()
    }

    private fun callGetNoticeList() {
        Utils.showProgress(requireContext())
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val studentId = preference!!.getString(preference!!.ID, "")
        val noticeapi: Call<NoticeResponse> = apiInterface.getNoticeListApi(studentId)
        noticeapi.enqueue(object : Callback<NoticeResponse> {
            override fun onResponse(call: Call<NoticeResponse>, response: Response<NoticeResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null) {
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.notice_found_successful), true)) {
                        binding.noticeRecyclerview.adapter = NoticeAdapter(body.data, noticeClickListener, requireContext())
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<NoticeResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string, requireContext()).show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewClicked(notice: Notice) {
        val builder = AlertDialog.Builder(requireContext());
        // set the custom layout
        val dialogBinding = NoticeDialogLayoutBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        val fromHtml = Html.fromHtml(notice.title, Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH)
        var end = fromHtml.indexOf("\n", fromHtml.length-4)
        if (end == -1)
            end = fromHtml.length
        dialogBinding.title.text = fromHtml.subSequence(0, end)
        dialogBinding.publishDate.text = notice.publishDate
        dialogBinding.message.text = notice.message
        // add a button
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        // create and show the alert dialog
        val dialog = builder.create();
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }
}