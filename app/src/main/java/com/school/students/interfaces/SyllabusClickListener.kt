package com.school.students.interfaces

import com.school.students.model.Syllabus

interface SyllabusClickListener {
    fun onDownloadClicked(syllabus: Syllabus)
}