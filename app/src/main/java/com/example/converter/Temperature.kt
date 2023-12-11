package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Temperature : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        supportActionBar?.title = "Temperature"
        val convertButton: Button = findViewById(R.id.convert)
        val outputText: TextView = findViewById(R.id.output)
        val inputText: EditText = findViewById(R.id.input)
        val fromValue: Spinner = findViewById(R.id.from_value)
        val toValue: Spinner = findViewById(R.id.to_value)
        val backButton: Button = findViewById(R.id.back)

        backButton.setOnClickListener {
            finish()
        }

        val options: ArrayAdapter<*> = ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line,
            arrayOf("°C", "°F")
        )
        fromValue.adapter = options
        toValue.adapter = options
        options.setDropDownViewResource(R.layout.spinner_style)

        convertButton.setOnClickListener {
            val inputValue = inputText.text.toString().replace(",", ".").toFloatOrNull()

            if (inputValue != null) {
                when (fromValue.selectedItemPosition) {
                    0 -> {
                        // From C
                        when (toValue.selectedItemPosition) {
                            // To C
                            0 -> outputText.text = "$inputValue °C"
                            // To F
                            1 -> outputText.text = "${(inputValue * 9 / 5) + 32} °F"
                        }
                    }
                    1 -> {
                        // From F
                        when (toValue.selectedItemPosition) {
                            // To C
                            0 -> outputText.text = "${(inputValue - 32) * 5 / 9} °C"
                            // To F
                            1 -> outputText.text = "$inputValue °F"
                        }
                    }
                }
            } else {
                outputText.text = "Invalid input"
            }

            outputText.visibility = View.VISIBLE
        }
    }
}