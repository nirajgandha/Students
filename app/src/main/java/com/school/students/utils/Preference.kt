package com.school.students.utils

import android.content.Context
import android.content.SharedPreferences
import com.school.students.R
import java.util.*

/**
 * Preference class to use SharedPreference class through out application. Use this class to store or retrieve data from SharedPreference.
 */
class Preference(context: Context) {
    /**
     * Preference key for userId
     */
    val ID = "ID"
    val PARENT_ID = "PARENT_ID"
    val ROLL_NO = "ROLL_NO"
    val GRNO = "GRNO"
    val ADMISSION_DATE = "ADMISSION_DATE"
    val STUDENT_FIRST_NAME = "STUDENT_FIRST_NAME"
    val STUDENT_LAST_NAME = "STUDENT_LAST_NAME"
    val IMAGE = "IMAGE"
    val MOBILE = "MOBILE"
    val RELIGION = "RELIGION"
    val CAST = "CAST"
    val DOB = "DOB"
    val GENDER = "GENDER"
    val current_address = "current_address"
    val permanent_address = "permanent_address"
    val category_id = "category_id"
    val blood_group = "blood_group"
    val adhar_no = "adhar_no"
    val annual_income_id = "annual_income_id"
    val father_name = "father_name"
    val father_phone = "father_phone"
    val father_occupation = "father_occupation"
    val father_pic = "father_pic"
    val mother_name = "mother_name"
    val mother_phone = "mother_phone"
    val mother_occupation = "mother_occupation"
    val mother_pic = "mother_pic"
    val guardian_name = "guardian_name"
    val guardian_phone = "guardian_phone"
    val guardian_occupation = "guardian_occupation"
    val guardian_pic = "guardian_pic"
    val sibling_id = "sibling_id"
    val class_id = "class_id"
    val section_id = "section_id"
    val loginId = "loginId"
    val className = "className"
    val sectionName = "sectionName"

    /**
     * Shared Preference instance
     */
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE) as SharedPreferences

    fun getString(key: String, default: String): String {
        return sharedPreferences.getString(key, default)!!
    }

    fun setString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    fun setInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    /**
     * Clears the all Shared Preference data
     */
    fun clearAllPreferenceData() {
        val editor = sharedPreferences.edit()
        editor.clear().commit()
    }

}