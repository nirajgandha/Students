package com.school.students.fragments

import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.school.students.R
import com.school.students.customui.materialcalendarview.EventDay
import com.school.students.customui.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.school.students.databinding.FragmentAttendanceBinding
import com.school.students.model.AttendanceListResponse
import com.school.students.retrofit_api.APIClient
import com.school.students.retrofit_api.APIInterface
import com.school.students.utils.Preference
import com.school.students.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class AttendanceFragment : Fragment() {

    var _binding : FragmentAttendanceBinding? = null
    val binding get() = _binding!!
    var preference : Preference ?= null
    val events: MutableList<EventDay> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference= Preference(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentAttendanceBinding.inflate(inflater)
        binding.backNavigation.setOnClickListener { requireActivity().onBackPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarView.setHeaderLabelColor(R.color.black)
        binding.calendarView.setHeaderColor(R.color.white)

        var year = binding.calendarView.currentPageDate.get(Calendar.YEAR).toString()
        callGetAttendanceList(year)

        binding.calendarView.setOnForwardPageChangeListener(object : OnCalendarPageChangeListener {
            override fun onChange() {
                val date = binding.calendarView.getHeaderName()
                val currentYear = date.split(" ")[2]
                for (str in date.split(" ")) {
                    Log.d("niraj", "onChange: $str")
                }
                if (currentYear != year) {
                    year = currentYear
                    callGetAttendanceList(year)
                }
            }
        })

        binding.calendarView.setOnPreviousPageChangeListener (object : OnCalendarPageChangeListener{
            override fun onChange() {
                val date = binding.calendarView.getHeaderName()
                val currentYear = date.split(" ")[2]
                if (currentYear != year){
                    year = currentYear
                    callGetAttendanceList(year)
                }
            }
        })

    }

    private fun callGetAttendanceList(year: String) {
        Utils.showProgress(requireContext())
        val classId = preference!!.getString(preference!!.class_id, "")
        val sectionId = preference!!.getString(preference!!.section_id, "")
        val studentId = preference!!.getString(preference!!.ID, "")
        val apiInterface = APIClient.getClient().create(APIInterface::class.java)
        val call: Call<AttendanceListResponse> = apiInterface.getAttendanceListApi(classId, sectionId, studentId, year)
        call.enqueue(object : Callback<AttendanceListResponse> {
            override fun onResponse(call: Call<AttendanceListResponse>, response: Response<AttendanceListResponse>) {
                Utils.hideProgress()
                val body = response.body()
                if (body != null){
                    val meta = body.meta
                    if (meta.message.equals(getString(R.string.attendance_found_successfully), true)){
                        val attendanceList = body.data
                        events.clear()
                        binding.calendarView.setEvents(events)
                        for (attendance in attendanceList){
                            val splits = getFormattedDateArray(attendance.date)
                            val calendar = Calendar.getInstance()
                            calendar.set(Calendar.YEAR, splits[0].toInt())
                            calendar.set(Calendar.MONTH, splits[1].toInt()-1)
                            calendar.set(Calendar.DAY_OF_MONTH, splits[2].toInt())
                            events.add(EventDay(calendar, setEventDot(attendance.titleName)))
                        }
                        binding.calendarView.setEvents(events)
                    } else {
                        showError(meta.message)
                    }
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<AttendanceListResponse>, t: Throwable) {
                Utils.hideProgress()
                showError("Error occurred!! Please try again later")
                t.printStackTrace()
            }

        })
    }

    private fun setEventDot(titleName: String): Drawable {
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_dots)
        if (titleName.equals("present", true)) {
            drawable?.setTint(resources.getColor(R.color.present, requireContext().theme))
        } else {
            drawable?.setTint(resources.getColor(R.color.absent, requireContext().theme))
        }

        //Add padding to too large icon
        return InsetDrawable(drawable, 100, 0, 100, 0)
    }

    private fun getFormattedDateArray(date_time : String): List<String> {
        val split = date_time.split(" ")
        return split[0].split("-")
    }

    private fun showError(string: String) {
        Utils.showSnackBar(binding.root, string, requireContext()).show()
    }
}