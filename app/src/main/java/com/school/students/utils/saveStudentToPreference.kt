package com.school.students.utils

import android.content.Context
import com.school.students.model.Student

object saveStudentToPreference {
    fun saveStudentPreference(context: Context, student: Student){
        val preference: Preference = Preference(context)
        preference.setString(preference.ID, student.id)
        preference.setString(preference.PARENT_ID, student.parentId)
        preference.setString(preference.ROLL_NO, student.rollNo)
        preference.setString(preference.GRNO, student.grno)
        preference.setString(preference.ADMISSION_DATE, student.admissionDate)
        preference.setString(preference.STUDENT_FIRST_NAME, student.firstname)
        preference.setString(preference.STUDENT_LAST_NAME, student.lastname)
        preference.setString(preference.IMAGE, student.image)
        preference.setString(preference.MOBILE, student.mobileno)
        preference.setString(preference.RELIGION, student.religion)
        preference.setString(preference.CAST, student.cast)
        preference.setString(preference.DOB, student.dob)
        preference.setString(preference.GENDER, student.gender)
        preference.setString(preference.current_address, student.currentAddress)
        preference.setString(preference.permanent_address, student.permanentAddress)
        preference.setString(preference.blood_group, student.bloodGroup)
        preference.setString(preference.adhar_no, student.adharNo)
        preference.setString(preference.annual_income_id, student.annualIncomeId)
        preference.setString(preference.father_name, student.fatherName)
        preference.setString(preference.father_phone, student.fatherPhone)
        preference.setString(preference.father_occupation, student.fatherOccupation)
        preference.setString(preference.mother_name, student.motherName)
        preference.setString(preference.mother_phone, student.motherPhone)
        preference.setString(preference.sibling_id, student.siblingId)
    }
}