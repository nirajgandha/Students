package com.school.students.interfaces

import com.school.students.model.ToDoActivityItem

interface ToDoClickListener {
    fun onViewClicked(toDoActivityItem: ToDoActivityItem)
}