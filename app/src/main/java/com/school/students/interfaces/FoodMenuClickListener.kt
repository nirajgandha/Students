package com.school.students.interfaces

import com.school.students.model.FoodMenu

interface FoodMenuClickListener {
    fun onViewClicked(foodMenu: FoodMenu)
    fun onDownloadClicked(foodMenu: FoodMenu)
}