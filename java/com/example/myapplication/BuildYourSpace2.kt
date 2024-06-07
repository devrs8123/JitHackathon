package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class BuildYourSpace2 : AppCompatActivity() {
    private var selectedMood: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.building_your_space2)

        val happyButton = findViewById<ImageButton>(R.id.happy_button)
        val calmButton = findViewById<ImageButton>(R.id.calm_button)
        val nervousButton = findViewById<ImageButton>(R.id.nervous_button)
        val scaredButton = findViewById<ImageButton>(R.id.scared_button)
        val tiredButton = findViewById<ImageButton>(R.id.tired_button)
        val sillyButton = findViewById<ImageButton>(R.id.silly_button)
        val worriedButton = findViewById<ImageButton>(R.id.worried_button)
        val frustratedButton = findViewById<ImageButton>(R.id.frustrated_button)
        val shyButton = findViewById<ImageButton>(R.id.shy_button)
        val sadButton = findViewById<ImageButton>(R.id.sad_button)
        val excitedButton = findViewById<ImageButton>(R.id.excited_button)
        val angryButton = findViewById<ImageButton>(R.id.angry_button)

        val moodClickListener =
            View.OnClickListener { view ->
                resetButtons()
                view.isSelected = true
                when (view.id) {
                    R.id.happy_button -> selectedMood = "Happy"
                    R.id.calm_button -> selectedMood = "Calm"
                    R.id.nervous_button -> selectedMood = "Nervous"
                    R.id.scared_button -> selectedMood = "Scared"
                    R.id.tired_button -> selectedMood = "Tired"
                    R.id.silly_button -> selectedMood = "Silly"
                    R.id.worried_button -> selectedMood = "Worried"
                    R.id.frustrated_button -> selectedMood = "Frustrated"
                    R.id.shy_button -> selectedMood = "Shy"
                    R.id.sad_button -> selectedMood = "Sad"
                    R.id.excited_button -> selectedMood = "Excited"
                    R.id.angry_button -> selectedMood = "Angry"
                }
            }

        happyButton.setOnClickListener(moodClickListener)
        calmButton.setOnClickListener(moodClickListener)
        nervousButton.setOnClickListener(moodClickListener)
        scaredButton.setOnClickListener(moodClickListener)
        tiredButton.setOnClickListener(moodClickListener)
        sillyButton.setOnClickListener(moodClickListener)
        worriedButton.setOnClickListener(moodClickListener)
        frustratedButton.setOnClickListener(moodClickListener)
        shyButton.setOnClickListener(moodClickListener)
        sadButton.setOnClickListener(moodClickListener)
        excitedButton.setOnClickListener(moodClickListener)
        angryButton.setOnClickListener(moodClickListener)

        val finishButton = findViewById<Button>(R.id.finish_button)
        finishButton.setOnClickListener {
            if (selectedMood != null) {
                val intent = Intent(
                    this@BuildYourSpace2,
                    AddingToYourSpaceActivity::class.java
                )
                intent.putExtra("SELECTED_MOOD", selectedMood)
                startActivity(intent)
            }
        }
    }

    private fun resetButtons() {
        findViewById<View>(R.id.happy_button).isSelected = false
        findViewById<View>(R.id.calm_button).isSelected = false
        findViewById<View>(R.id.nervous_button).isSelected = false
        findViewById<View>(R.id.scared_button).isSelected = false
        findViewById<View>(R.id.tired_button).isSelected = false
        findViewById<View>(R.id.silly_button).isSelected = false
        findViewById<View>(R.id.worried_button).isSelected = false
        findViewById<View>(R.id.frustrated_button).isSelected = false
        findViewById<View>(R.id.shy_button).isSelected = false
        findViewById<View>(R.id.sad_button).isSelected = false
        findViewById<View>(R.id.excited_button).isSelected = false
        findViewById<View>(R.id.angry_button).isSelected = false
    }
}
