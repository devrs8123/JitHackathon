package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class AddingToYourSpaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adding_to_you_space)

        // Find the CardView by its ID
        val cardView: CardView = findViewById(R.id.cardView)
        val cardView2: CardView = findViewById(R.id.cardView3)

        val selectedMood = intent.getStringExtra("SELECTED_MOOD")
        val moodTextView: TextView = findViewById(R.id.text_addingtoyourspace2)
        moodTextView.text = "Your selected mood is $selectedMood . Would You Like To Talk To A Therapist?"
        // Populate data to the CardView
        //val headingText = "Your Heading"
        //val descriptionText = "Your Description"

        //val textViewHeading = cardView.findViewById<TextView>(R.id.textViewHeading)
        //val textViewDescription = cardView.findViewById<TextView>(R.id.textViewDescription)

        //textViewHeading.text = headingText
        //textViewDescription.text = descriptionText

        // You can load the image programmatically here if needed
        // val imageView = cardView.findViewById<ImageView>(R.id.imageView)
        // imageView.setImageResource(R.drawable.your_image)
        cardView2.setOnClickListener{
            val intent = Intent(this, GetATherapistActivity::class.java)
            startActivity(intent)
        }
        cardView.setOnClickListener{
            val intent = Intent(this, NickNameActivity::class.java)
            startActivity(intent)
        }
    }
}