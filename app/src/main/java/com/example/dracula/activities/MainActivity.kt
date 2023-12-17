package com.example.dracula.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.dracula.R
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var isRecording = false
    private val recordedDates = mutableListOf<String>()
    private val itemsAdded = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sizeSpinner: Spinner = findViewById(R.id.sizeSpinner)
        val addButton: Button = findViewById(R.id.addButton)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setTitleTextAppearance(this, R.style.CustomToolbarStyle)

        // Populate the spinner with size options
        val sizeOptions = arrayOf("S size", "M size", "L size")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sizeOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sizeSpinner.adapter = adapter

/*        addButton.setOnClickListener {
            // Add the selected size to the list of items
            val selectedSize = sizeSpinner.selectedItem as String
            itemsAdded.add(selectedSize)
            Toast.makeText(this, "$selectedSize added", Toast.LENGTH_SHORT).show()
        }*/
    }

    fun onViewLogsButtonClick(view: View) {
        val intent = Intent(this, LogsActivity::class.java)
        intent.putStringArrayListExtra("recordedDates", ArrayList(recordedDates))
        intent.putStringArrayListExtra("itemsAdded", ArrayList(itemsAdded))
        startActivity(intent)
    }

    fun onStartStopButtonClick(view: View) {
        val startStopButton = view as ToggleButton

        val sizeSpinner: Spinner = findViewById(R.id.sizeSpinner)
        val addButton: Button = findViewById(R.id.addButton)
        val referenceButton: Button = findViewById(R.id.referenceButton)
        val viewLogsButton: Button = findViewById(R.id.viewLogsButton)
        val statusTextView: TextView = findViewById(R.id.statusTextView)

        if (startStopButton.isChecked) {
            // User pressed the button in the START state
            sizeSpinner.visibility = View.VISIBLE
            addButton.visibility = View.VISIBLE
            referenceButton.visibility = View.VISIBLE
            viewLogsButton.visibility = View.INVISIBLE
            val currentDate = getCurrentDateTime()
            statusTextView.text = "Stop Menstrual Cycle"
            recordedDates.add(currentDate)
        } else {
            // User pressed the button in the STOP state
            sizeSpinner.visibility = View.INVISIBLE
            addButton.visibility = View.INVISIBLE
            referenceButton.visibility = View.INVISIBLE
            viewLogsButton.visibility = View.VISIBLE
            val currentDate = getCurrentDateTime()
            statusTextView.text = "Start Menstrual Cycle"
            recordedDates[recordedDates.size - 1] += " - $currentDate"

            // Launch new activity in the STOP state
            val intent = Intent(this, EndOfCycleActivity::class.java)
            intent.putStringArrayListExtra("itemsAdded", ArrayList(itemsAdded))
            startActivity(intent)
        }
    }

    fun onAddButtonClick(view: View) {
        val sizeSpinner: Spinner = findViewById(R.id.sizeSpinner)

        // Add the selected size to the list of items
        val selectedSize = sizeSpinner.selectedItem as String
        itemsAdded.add(selectedSize)

        // Notify the user that the size was added
        Toast.makeText(this, "$selectedSize added", Toast.LENGTH_SHORT).show()
    }

    fun onReferenceButtonClick(view: View) {
        // Open a new view to show the image
        val intent = Intent(this, ReferenceActivity::class.java)
        startActivity(intent)
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }
}