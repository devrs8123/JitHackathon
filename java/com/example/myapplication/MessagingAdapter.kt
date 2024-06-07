package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MessageItemBinding
import com.example.myapplication.data.Message
import com.example.myapplication.util.Constants.RECEIVE_ID
import com.example.myapplication.util.Constants.SEND_ID

class MessagingAdapter : RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {
    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            when (message.id) {
                SEND_ID -> {
                    binding.tvMessage.text = message.message
                    binding.tvMessage.visibility = View.VISIBLE
                    binding.tvBotMessage.visibility = View.GONE
                }
                RECEIVE_ID -> {
                    binding.tvBotMessage.text = message.message
                    binding.tvBotMessage.visibility = View.VISIBLE
                    binding.tvMessage.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]
        holder.bind(currentMessage)
    }

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }
}
