package com.school.students.customui.materialcalendarview.exceptions

object InvalidCustomLayoutException : RuntimeException("YOUR CUSTOM CALENDAR DAY LAYOUT MUST CONTAIN A TextView WITH android:id=\"@+id/dayLabel\"")