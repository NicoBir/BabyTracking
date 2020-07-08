package com.example.babytracking

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val baby = findViewById(R.id.text_baby) as TextView
        val milestones = findViewById(R.id.text_milestones) as TextView
        val breast = findViewById(R.id.text_breast) as TextView
        val sleep = findViewById(R.id.text_sleep) as TextView
        val diapers = findViewById(R.id.text_diapers) as TextView


        baby.setOnClickListener {
            val babyIntent = Intent(this@MainActivity, BabyActivity::class.java)
            startActivity(babyIntent)
        }

        milestones.setOnClickListener {
            val milestonesIntent = Intent(this@MainActivity, MilestoneActivity::class.java)
            startActivity(milestonesIntent)
        }

        breast.setOnClickListener {
            val breastIntent = Intent(this@MainActivity, BreastActivity::class.java)
            startActivity(breastIntent)
        }

        sleep.setOnClickListener {
            val sleepIntent = Intent(this@MainActivity, SleepActivity::class.java)
            startActivity(sleepIntent)
        }

        diapers.setOnClickListener {
            val diaperIntent = Intent(this@MainActivity, DiaperActivity::class.java)
            startActivity(diaperIntent)
        }

    }
}