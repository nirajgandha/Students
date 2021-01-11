package com.school.students.interfaces

import com.school.students.model.Homework

interface HomeWorkClickListener {
    fun onViewClicked(homework: Homework)
    fun onDownloadClicked(homework: Homework)
    fun onUploadClicked(homework: Homework)
}