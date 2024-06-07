package com.example.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import com.example.myapplication.data.Message // Import your custom Message class
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.util.BotResponse
import com.example.myapplication.util.Constants.HELP
import com.example.myapplication.util.Constants.OPEN_GOOGLE
import com.example.myapplication.util.Constants.OPEN_SEARCH
import com.example.myapplication.util.Constants.RECEIVE_ID
import com.example.myapplication.util.Constants.SEND_ID
import com.example.myapplication.util.Time
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatBot : AppCompatActivity() {

    private val TAG = "MainActivity"
    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Peter","Francesca","Luigi","Igor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatbot)

        recyclerView()

        clickEvents()

        title = "ChatBot"

        val random = (0..3).random()
        customMessage("Hello! Today you're speaking with ${botList[random]}, how may i help?")
    }
    private fun clickEvents(){
        val btn_send = findViewById<Button>(R.id.btn_send)
        val et_message = findViewById<EditText>(R.id.et_message)
        val rv_messages = findViewById<RecyclerView>(R.id.rv_messages)

        btn_send.setOnClickListener {
            sendMessage()
        }
        et_message.setOnClickListener{
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main){
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        val rv_messages = findViewById<RecyclerView>(R.id.rv_messages)
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage(){
        val et_message = findViewById<EditText>(R.id.et_message)
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()
        val rv_messages = findViewById<RecyclerView>(R.id.rv_messages)

        if(message.isNotEmpty()){
            et_message.setText("")
            val timeStamp = Time.timeStamp()
            adapter.insertMessage(com.example.myapplication.data.Message(message , SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()
        val rv_messages = findViewById<RecyclerView>(R.id.rv_messages)

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponse(message)
                //Adds it to our local list
                // Instead of just using `Message`, specify the full path of your custom Message class:

                messagesList.add(com.example.myapplication.data.Message(response, RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                adapter.insertMessage(com.example.myapplication.data.Message(response, RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(adapter.itemCount - 1)


                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }

                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                    HELP -> {
                        val phoneNumber = "+91 8618190541" // Replace this with the phone number you want to call

                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber")
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val rv_messages = findViewById<RecyclerView>(R.id.rv_messages)

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun customMessage(message: String){
        val rv_messages = findViewById<RecyclerView>(R.id.rv_messages)
        GlobalScope.launch{
            delay(1000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.timeStamp()
                adapter.insertMessage(com.example.myapplication.data.Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount-1)
            }
        }
    }

}

