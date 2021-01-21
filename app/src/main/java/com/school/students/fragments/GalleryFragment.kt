package com.school.students.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.school.students.R
import com.school.students.activity.MainActivity
import com.school.students.activity.SlideShowActivity
import com.school.students.adapter.CoCurriculumAdapter
import com.school.students.adapter.GalleryAdapter
import com.school.students.databinding.FragmentGalleryBinding
import com.school.students.interfaces.GalleryClickListener
import com.school.students.model.CoCurriculumResponse
import com.school.students.model.GalleryItem
import com.school.students.model.GalleryResponse
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Preference
import com.school.students.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GalleryFragment : Fragment(), GalleryClickListener {

    private var _binding : FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null
    private val galleryClickListener: GalleryClickListener = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = Preference(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentGalleryBinding.inflate(inflater)
        binding.imgSettings.setOnClickListener { (requireActivity() as MainActivity).startSettingsActivity() }
        binding.backNavigation.setOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.galleryRecyclerview.layoutManager = gridLayoutManager
        callGalleryList()
    }

    private fun callGalleryList() {
        Utils.showProgress(requireContext())
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val studentId = preference!!.getString(preference!!.ID, "")
        apiInterface.getGalleryListApi(studentId)
                .enqueue(object : Callback<GalleryResponse> {
            override fun onResponse(call: Call<GalleryResponse>, response: Response<GalleryResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null) {
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.gallery_found_successful), true)) {
                       binding.galleryRecyclerview.adapter = GalleryAdapter(body.data, galleryClickListener, requireContext())
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<GalleryResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string, requireContext()).show()
    }

    override fun onItemClick(galleryItem: GalleryItem) {
        val intent = Intent(requireContext(), SlideShowActivity::class.java)
        intent.putStringArrayListExtra(getString(R.string.galleryImagesUrlList), galleryItem.images)
        startActivity(intent)
    }
}