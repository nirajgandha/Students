package com.school.students.customui.materialcalendarview.listeners

import com.school.students.customui.materialcalendarview.EventDay

/**
 * This interface is used to handle long clicks on calendar cells
 *
 * Created by Applandeo Team.
 */

interface OnDayLongClickListener {
    fun onDayLongClick(eventDay: EventDay)
}