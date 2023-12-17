package com.example.dracula.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dracula.R
import android.widget.ImageView

class ReferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reference)

        // Load and display the reference image
        val referenceImage: ImageView = findViewById(R.id.referenceImageView)
        referenceImage.setImageResource(R.drawable.pad)
    }
}