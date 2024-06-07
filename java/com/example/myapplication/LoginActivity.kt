package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Find the button by its ID
        val nextButton: Button = findViewById(R.id.button_next)

        // Set an OnClickListener on the button
        nextButton.setOnClickListener {
            // Create an intent to start the DashboardActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}