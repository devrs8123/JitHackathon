package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.telephony.SmsManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class DashboardActivity : AppCompatActivity() {

    private val SPEECH_REQUEST_CODE = 123
    private val RECORD_AUDIO_PERMISSION_CODE = 456
    private val NOTIFICATION_CHANNEL_ID = "your_channel_id"
    private val NOTIFICATION_PERMISSION_REQUEST_CODE = 1001
    private val LOCATION_PERMISSION_REQUEST_CODE = 1002

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val buttonSos: Button = findViewById(R.id.sos)
        buttonSos.setOnClickListener {
            val intent = Intent(this, SOS::class.java)
            startActivity(intent)
        }

        val btnBookSession: Button = findViewById(R.id.btn_book_session)
        btnBookSession.setOnClickListener {
            val intent = Intent(this, GetATherapistActivity::class.java)
            startActivity(intent)
        }

        val imageView = findViewById<ImageView>(R.id.cardimg1)
        imageView.setOnClickListener {
            val url = "https://www.inc.com/john-brandon/7-ways-to-increase-your-mental-energy-right-now.html"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val imageView2 = findViewById<ImageView>(R.id.cardimg2)
        imageView2.setOnClickListener {
            val url = "https://youtu.be/eBSeCp__xhI?si=i8EtE5MM55VRGC8S"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val imageView3 = findViewById<ImageView>(R.id.cardimg3)
        imageView3.setOnClickListener {
            val url = "https://youtu.be/SAlWns-6gAY?si=2m1UYz687q6yBgz1"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val buttonPlayVideo1 = findViewById<ImageView>(R.id.stress)
        buttonPlayVideo1.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }

        val buttonPlayVideo2 = findViewById<ImageView>(R.id.inspire)
        buttonPlayVideo2.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }

        val buttonPlayVideo3 = findViewById<ImageView>(R.id.productivity)
        buttonPlayVideo3.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }

        val buttonPlayVideo4 = findViewById<ImageView>(R.id.wellness)
        buttonPlayVideo4.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }

        val buttonPlayVideo5 = findViewById<ImageView>(R.id.health)
        buttonPlayVideo5.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }

        // Set activity title
        val titleText = intent.getStringExtra("TITLE_EXTRA")
        title = "$titleText's space"

        val chatbot = findViewById<ImageView>(R.id.chatbot)
        chatbot.setOnClickListener {
            checkPermissionAndStartSpeechRecognition()
            val intent = Intent(this, ChatBot::class.java)
            startActivity(intent)
        }

        // Check location permissions
        checkLocationPermissions()
    }

    private fun checkLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun checkPermissionAndStartSpeechRecognition() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_CODE
            )
        } else {
            startSpeechRecognition()
        }
    }

    private fun startSpeechRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...")
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val transcribedText = results?.get(0) ?: ""
            processVoiceInput(transcribedText)
        }
    }

    private fun processVoiceInput(transcribedText: String) {
        val sensitiveWords = listOf("suicide", "depression", /* Add more sensitive words */)

        for (word in sensitiveWords) {
            if (transcribedText.contains(word, ignoreCase = true)) {
                sendSupportiveNotification()
                getCurrentLocationAndSendSMS("+91 8618190541", "Alert - Emergency Detected from user , kindly get in touch ASAP.")
                break
            }
        }
    }

    private fun getCurrentLocationAndSendSMS(phoneNumber: String, message: String) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val messageWithLocation = "$message Location: https://maps.google.com/?q=${it.latitude},${it.longitude}"
                        sendSMS(phoneNumber, messageWithLocation)
                    }
                }
        }
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), NOTIFICATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun sendSupportiveNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Your Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.FOREGROUND_SERVICE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.FOREGROUND_SERVICE),
                NOTIFICATION_PERMISSION_REQUEST_CODE
            )
        } else {
            val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.chatbot2)
                .setContentTitle("Hey, I'm always here for you")
                .setContentText("Do you want to talk?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(this)) {
                notify(1, builder.build())
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_AUDIO_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startSpeechRecognition()
                }
            }
            NOTIFICATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSupportiveNotification()
                }
            }
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Location permission granted
                }
            }
        }
    }
}
