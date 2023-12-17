package com.example.dracula.activities

import android.content.Intent
import android.os.Bundle
import com.example.dracula.R
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LogsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)

        val recordedDates = intent.getStringArrayListExtra("recordedDates") ?: ArrayList()
        val itemsAdded = intent.getStringArrayListExtra("itemsAdded") ?: ArrayList()
        val logsLayout: LinearLayout = findViewById(R.id.logsLayout)

        for (i in recordedDates.indices) {
            val dateRange = recordedDates[i]
            val dateButton = Button(this)
            dateButton.text = dateRange
            dateButton.setOnClickListener {
                // Handle date button click
                navigateToItemsScreen(itemsAdded.getOrNull(i)?.let { arrayListOf(it) } ?: arrayListOf())
            }
            logsLayout.addView(dateButton)
        }
    }

    private fun navigateToItemsScreen(items: ArrayList<String>) {
        val intent = Intent(this, ItemsActivity::class.java)
        intent.putStringArrayListExtra("items", items)
        startActivity(intent)
    }
}
