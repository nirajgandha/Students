package com.school.students.interfaces

import com.school.students.model.Student

interface StudentSelectedListener {
    fun onStudentSelected(student: Student)
}