package com.example.babytracking

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity

class SleepActivity : AppCompatActivity() {

    var mLullaby: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_layout)
        val saveButton = findViewById<Button>(R.id.sleep_button_save)
        val hourEdit = findViewById<EditText>(R.id.sleep_hour)
        val lengthEdit = findViewById<EditText>(R.id.sleep_length)
        val qualityEdit = findViewById<EditText>(R.id.sleep_quality)
        val mLullabySpinner: Spinner =
            findViewById(com.example.babytracking.R.id.spinner_sleep_lullaby);

        if (mLullabySpinner != null) {
            val lullabySpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.array_yes_no,
                android.R.layout.simple_spinner_item
            )

            // Specify dropdown layout style - simple list view with 1 item per line
            lullabySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            mLullabySpinner.adapter = lullabySpinnerAdapter


            mLullabySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View, pos: Int, id: Long
                    ) {
                        val selection = parent.getItemAtPosition(pos) as String
                        if (!TextUtils.isEmpty(selection)) {
                            if (selection == getString(R.string.no_string)) {
                                mLullaby = false
                            } else if (selection == getString(R.string.yes_string)) {
                                mLullaby = true
                            }

                        }
                    }

                    // Because AdapterView is an abstract class, onNothingSelected must be defined
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        mLullaby = false
                    }
                }
        }

        saveButton.setOnClickListener {
            val hourText = hourEdit.text;
            val lengthText = lengthEdit.text;
            val qualText = qualityEdit.text;
            var lulText = getString(R.string.no_string)


            if (mLullaby == true) {
                lulText = getString(R.string.yes_string)
            } else {
                lulText = getString(R.string.no_string)
            }

            Toast.makeText(
                this@SleepActivity,
                "Hour = " + hourText + ", \n Length = " + lengthText
                        + ", \n Quality = " + qualText + ", \n Lullaby = " + lulText,
                Toast.LENGTH_SHORT
            ).show()
        }


    }
}
