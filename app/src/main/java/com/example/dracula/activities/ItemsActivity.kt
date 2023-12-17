package com.example.dracula.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.dracula.R
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val items = intent.getStringArrayListExtra("items") ?: ArrayList()
        val itemsTextView: TextView = findViewById(R.id.itemsTextView)
        val itemsListView: ListView = findViewById(R.id.itemsListView)

        if (items.isNotEmpty()) {
            itemsTextView.text = "Items:"
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
            itemsListView.adapter = adapter
        } else {
            itemsTextView.text = "No items to display."
        }
    }
}