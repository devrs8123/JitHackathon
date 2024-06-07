package com.example.myapplication


import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var splashText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        videoView = findViewById(R.id.videoView)
        splashText = findViewById(R.id.splashText)

        // Set your video file path or URI
        val videoPath = "android.resource://" + packageName + "/" + R.raw.splash_video // Change this to your video file in raw folder
        val videoUri = Uri.parse(videoPath)

        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mp: MediaPlayer ->
            // Start video playback and show text after video preparation
            mp.start()
            splashText.alpha = 0f
            splashText.animate().alpha(1f).duration = 5000
            splashText.postDelayed({
                startActivity(Intent(this@SplashScreenActivity, BuildYourSpace2::class.java))
                finish()
            }, 15000) // Delay for 30 seconds
        }
    }
}