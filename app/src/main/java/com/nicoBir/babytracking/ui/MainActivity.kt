package com.nicoBir.babytracking.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nicoBir.babytracking.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        
        /*val baby: TextView = findViewById(R.id.text_baby)
        val health: TextView = findViewById(R.id.text_health)
        val milestones: TextView = findViewById(R.id.text_milestones)
        val breast: TextView = findViewById(R.id.text_breast)
        val sleep: TextView = findViewById(R.id.text_sleep)
        val diapers: TextView= findViewById(R.id.text_diapers)*/
    
    
        text_baby.setOnClickListener {
            val babyIntent = Intent(this@MainActivity, BabyActivity::class.java)
            startActivity(babyIntent)
        }
    
        text_health.setOnClickListener {
            val healthIntent = Intent(this@MainActivity, HealthActivity::class.java)
            startActivity(healthIntent)
        }
    
        text_milestones.setOnClickListener {
            val milestonesIntent = Intent(this@MainActivity, MilestoneActivity::class.java)
            startActivity(milestonesIntent)
        }
    
        text_breast.setOnClickListener {
            val breastIntent = Intent(this@MainActivity, BreastActivity::class.java)
            startActivity(breastIntent)
        }
    
        text_sleep.setOnClickListener {
            val sleepIntent = Intent(this@MainActivity, SleepEditActivity::class.java)
            startActivity(sleepIntent)
        }
    
        text_diapers.setOnClickListener {
            val diaperIntent = Intent(this@MainActivity, DiaperActivity::class.java)
            startActivity(diaperIntent)
        }
        
    }
}
