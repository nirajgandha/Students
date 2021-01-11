package com.school.students.interfaces

import com.school.students.model.Notice

interface NoticeClickListener {
    fun onViewClicked(notice: Notice)
}