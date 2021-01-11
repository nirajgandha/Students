package com.school.students.fragments

import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.school.students.R
import com.school.students.adapter.HomeworkAdapter
import com.school.students.databinding.FragmentHomeWorkBinding
import com.school.students.databinding.HomeworkDialogLayoutBinding
import com.school.students.interfaces.HomeWorkClickListener
import com.school.students.model.GetHomeworkResponse
import com.school.students.model.Homework
import com.school.students.model.Notice
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Preference
import com.school.students.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeWorkFragment : Fragment(), HomeWorkClickListener {

    private var _binding : FragmentHomeWorkBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private val homeWorkClickListener: HomeWorkClickListener = this
    private var homeworkArrayList: ArrayList<Homework>? = null
    private var homeworkAdapter: HomeworkAdapter? = null
    private var downloadManager: DownloadManager?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = Preference(requireContext())
        downloadManager = requireContext().getSystemService(DOWNLOAD_SERVICE) as DownloadManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHomeWorkBinding.inflate(inflater)
        homeworkArrayList = ArrayList()
        binding.backNavigation.setOnClickListener { requireActivity().onBackPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.homeworkRecyclerview.layoutManager = linearLayoutManager
        callGetHomeworkListApi()
    }

    private fun callGetHomeworkListApi() {
        Utils.showProgress(requireContext())
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val classId = preference!!.getString(preference!!.class_id, "")
        val sectionId = preference!!.getString(preference!!.section_id, "")
        val studentId = preference!!.getString(preference!!.ID, "")
        val loginApi: Call<GetHomeworkResponse> = apiInterface.getHomeWorkListApi(classId, sectionId, studentId)
        loginApi.enqueue(object : Callback<GetHomeworkResponse> {
            override fun onResponse(call: Call<GetHomeworkResponse>, response: Response<GetHomeworkResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null) {
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.homework_found_successful), true)) {
                        binding.homeworkRecyclerview.adapter = HomeworkAdapter(body.data, homeWorkClickListener, requireContext())
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<GetHomeworkResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string, requireContext()).show()
    }

    override fun onViewClicked(homework: Homework) {
        val builder = AlertDialog.Builder(requireContext());
        // set the custom layout
        val dialogBinding = HomeworkDialogLayoutBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        dialogBinding.title.text = homework.subjectName
        dialogBinding.classNumber.text = getString(R.string.str_class_section, "${homework.className}${homework.sectionName}")
        dialogBinding.homeworkDate.text = getString(R.string.date_s, homework.homeworkDate)
        dialogBinding.descriptionValue.text = homework.description
        if (homework.homeworkTeacherComment.isEmpty()){
            dialogBinding.tvComment.visibility = View.GONE
            dialogBinding.commentValue.visibility = View.GONE
        } else {
            dialogBinding.tvComment.visibility = View.VISIBLE
            dialogBinding.commentValue.visibility = View.VISIBLE
            dialogBinding.commentValue.text = homework.homeworkTeacherComment
        }
        // add a button
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        // create and show the alert dialog
        val dialog = builder.create();
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show();
    }

    override fun onDownloadClicked(homework: Homework) {
        val homeworkDocuments = homework.document
        for (documentItem in homeworkDocuments) {
            val uri = Uri.parse(documentItem.downloadLink)
            val request = DownloadManager.Request(uri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setAllowedOverRoaming(true)
            request.setTitle(documentItem.originalName)
            request.setDescription("Downloading ${documentItem.originalName}")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/${getString(R.string.app_name)}/${documentItem.originalName}")
            downloadManager!!.enqueue(request)
        }
    }

    override fun onUploadClicked(homework: Homework) {
        //TODO "Not yet implemented"
    }
}