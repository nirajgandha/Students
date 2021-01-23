package com.school.students.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.students.R
import com.school.students.activity.MainActivity
import com.school.students.databinding.FragmentHomeBinding
import com.school.students.utils.Preference

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var preference: Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        preference = Preference(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadData() {
        binding.imgSettings.setOnClickListener { (requireActivity() as MainActivity).startSettingsActivity() }
        binding.studentNameTextView.text = "${preference!!.getString(preference!!.STUDENT_FIRST_NAME, "")} ${preference!!.getString(preference!!.STUDENT_LAST_NAME, "")}"
        binding.homeworkCl.setOnClickListener { (requireActivity() as MainActivity).onItemClick(getString(R.string.menu_homework)) }
        binding.attendanceCl.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(AttendanceFragment()) }
        binding.noticeBoardCl.setOnClickListener { (requireActivity() as MainActivity).onItemClick(getString(R.string.menu_notice)) }
        binding.syllabusCl.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(SyllabusFragment()) }
        binding.feeCl.setOnClickListener { (requireActivity() as MainActivity).onItemClick(getString(R.string.menu_fee)) }
        binding.calendarCl.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(CalendarFragment()) }
        binding.toDoActivityCl.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(ToDoActivityFragment()) }
        binding.galleryCl.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(GalleryFragment()) }
        binding.coCurriculumActivityCl.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(CoCurriculumFragment()) }
        binding.foodMenuCl.setOnClickListener { (requireActivity() as MainActivity).openOtherFragment(FoodMenuFragment()) }
    }
}