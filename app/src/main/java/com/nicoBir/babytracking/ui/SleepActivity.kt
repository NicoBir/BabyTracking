package com.nicoBir.babytracking.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.nicoBir.babytracking.R
import kotlinx.android.synthetic.main.sleep_layout.*

class SleepActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.sleep_layout)
    
        sleep_button_edit.setOnClickListener {
            val sleepIntent = Intent(this@SleepActivity, SleepActivity::class.java)
            startActivity(sleepIntent)
        }
        
        
    }
}