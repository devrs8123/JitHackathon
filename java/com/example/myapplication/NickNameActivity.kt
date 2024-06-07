package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class NickNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nick_name)

        val edt_nickname: EditText = findViewById(R.id.edt_nickname)
        val btn_nickname: Button = findViewById(R.id.btn_nickname)

        //to set title


        // Disable the button initially
        btn_nickname.isEnabled = false

        // Enable/disable button based on text input
        edt_nickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btn_nickname.isEnabled = s?.isNotBlank() ?: false

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btn_nickname.setOnClickListener {
            val text2 = edt_nickname.text.toString().trim()
            // Navigate to the next activity
            if (text2.isNotEmpty()) {
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("TITLE_EXTRA", text2)
                startActivity(intent)
            }
        }
    }
}

