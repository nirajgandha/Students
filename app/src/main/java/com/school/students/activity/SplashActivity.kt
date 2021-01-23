package com.school.students.activity

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.school.students.R
import com.school.students.utils.Preference

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT){
            requestedOrientation = Configuration.ORIENTATION_PORTRAIT
        }
        setContentView(R.layout.activity_splash)
        val preference = Preference(this)
        val id = preference.getString(preference.ID, "")

        Handler().postDelayed({
                              val intent = if (id.isEmpty()){
                                  preference.clearAllPreferenceData()
                                  Intent(this, LoginActivity::class.java)
                              } else {
                                  Intent(this, MainActivity::class.java)
                              }
            startActivity(intent)
            finish()
        },2000)
    }
}