package com.example.calendertask.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendertask.R
import com.example.calendertask.utils.extensions.addFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }

    private fun openFragment() {
        supportFragmentManager.addFragment(CalendarFragment(), R.id.fragment_container)
    }
}