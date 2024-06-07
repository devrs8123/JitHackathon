package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class BuildingYourSpaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.building_your_space)

        //setting title
        val titleText = intent.getStringExtra("TITLE_EXTRA")
        title = titleText // Sets the entered text as the title of the activity

        val checkbox1_1: CheckBox = findViewById(R.id.checkbox1_1)
        val checkbox2_1: CheckBox = findViewById(R.id.checkbox2_1)
        val checkbox1_2: CheckBox = findViewById(R.id.checkbox1_2)
        val checkbox2_2: CheckBox = findViewById(R.id.checkbox2_2)
        val btn_buildingyourspace: Button = findViewById(R.id.btn_building)

        // Set onClickListeners for checkboxes
        checkbox1_1.setOnCheckedChangeListener { _, isChecked ->
            // Handle checkbox state change
            // For demonstration purposes, you can perform actions here when checkbox state changes
        }

        checkbox2_1.setOnCheckedChangeListener { _, isChecked ->
            // Handle checkbox state change
        }

        checkbox1_2.setOnCheckedChangeListener { _, isChecked ->
            // Handle checkbox state change
        }

        checkbox2_2.setOnCheckedChangeListener { _, isChecked ->
            // Handle checkbox state change
        }

        btn_buildingyourspace.setOnClickListener{
            val intent = Intent(this, AddingToYourSpaceActivity::class.java)
            startActivity(intent)
        }
    }
}