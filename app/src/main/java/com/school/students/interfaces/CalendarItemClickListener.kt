package com.school.students.interfaces

import com.school.students.model.CalendarItem

interface CalendarItemClickListener {
    fun onViewClicked(calendarItem: CalendarItem)
    fun onDownloadClicked(calendarItem: CalendarItem)
}