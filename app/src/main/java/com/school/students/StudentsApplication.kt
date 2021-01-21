package com.school.students

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class StudentsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}