package com.example.babytracking

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BreastActivity : AppCompatActivity() {

    val breastButton = findViewById<Button>(R.id.breast_button_save)
    val hourEdit = findViewById<EditText>(R.id.breast_hour)
    val lengthEdit = findViewById<EditText>(R.id.breast_length)
    val babyEdit = findViewById<EditText>(R.id.breast_baby)
    val motherEdit = findViewById<EditText>(R.id.breast_mother)
    var mBreast: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.breast_layout)

        val mBreastSpinner: Spinner =
            findViewById(com.example.babytracking.R.id.spinner_breast);

        if (mBreastSpinner != null) {
            val breastSpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.array_breast,
                android.R.layout.simple_spinner_item
            )

            // Specify dropdown layout style - simple list view with 1 item per line
            breastSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            mBreastSpinner.adapter = breastSpinnerAdapter


            mBreastSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View, pos: Int, id: Long
                    ) {
                        val selection = parent.getItemAtPosition(pos) as String
                        if (!TextUtils.isEmpty(selection)) {
                            if (selection == getString(R.string.left_breast_string)) {
                                mBreast = true // Right
                            } else if (selection == getString(R.string.right_breast_string)) {
                                mBreast = false // Left
                            }

                        }
                    }

                    // Because AdapterView is an abstract class, onNothingSelected must be defined
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        mBreast = false
                    }
                }
        }

        breastButton.setOnClickListener {
            val hourText = hourEdit.text;
            val lengthText = lengthEdit.text;
            val babyText = babyEdit.text;
            val motherText = motherEdit.text;
            var breastText = getString(R.string.left_breast_string)


            if (mBreast == true) {
                breastText = getString(R.string.right_breast_string)
            } else {
                breastText = getString(R.string.left_breast_string)
            }

            Toast.makeText(
                this@BreastActivity,
                "Hour = " + hourText + ", \n Length = " + lengthText
                        + ", \n Breast = " + breastText + ", \n Session for baby = " + babyText
                        + ", \n Session for mother = " + motherText,
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
