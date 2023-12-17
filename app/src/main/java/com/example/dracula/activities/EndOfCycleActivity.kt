package com.example.dracula.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dracula.R
import android.widget.TextView

class EndOfCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endofcycle)

        val itemsAdded = intent.getStringArrayListExtra("itemsAdded") ?: ArrayList()
        val itemsCount = itemsAdded.size

        val messageTextView: TextView = findViewById(R.id.messageTextView)
        messageTextView.text = getString(R.string.stop_state_message, itemsCount)
    }
}