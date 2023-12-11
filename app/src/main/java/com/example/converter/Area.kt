package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Area : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area)
        supportActionBar?.title = "Area"
        val convertButton: Button = findViewById(R.id.convert)
        val outputText: TextView = findViewById(R.id.output)
        val inputText: EditText = findViewById(R.id.input)
        val fromValue: Spinner = findViewById(R.id.from_value)
        val toValue: Spinner = findViewById(R.id.to_value)
        val backButton:Button = findViewById(R.id.back)

        backButton.setOnClickListener {
            finish()
        }

        val options: ArrayAdapter<*> = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,
            arrayOf("Meter sqr","Centimeter sqr","Hectare")
        )
        fromValue.adapter = options
        toValue.adapter = options
        options.setDropDownViewResource(R.layout.spinner_style)
        convertButton.setOnClickListener {
            if (inputText.text.isEmpty() or (inputText.text.toString() == "."))
                outputText.text = "Please enter some value"
            else{
                when(fromValue.selectedItemPosition){
                    // From Meter Square
                    0 ->{
                        when(toValue.selectedItemPosition){
                            // To Meter Square
                            0 -> outputText.text = "${ inputText.text.toString().toFloat()} Meter sqr"
                            // To Centimeter Square
                            1 -> outputText.text = "${ inputText.text.toString().toFloat() * 100 *100} Centimeter sqr"
                            // To Square Feet
                            2 -> outputText.text = "${ inputText.text.toString().toFloat() / 10000}"+" Hectare"

                        }
                    }
                    // From Centimeter Square
                    1 ->{
                        when(toValue.selectedItemPosition){
                            // To Meter Square
                            0 -> outputText.text = "${ inputText.text.toString().toFloat()/10000} Meter sqr"
                            // To Centimeter Square
                            1 -> outputText.text = "${ inputText.text.toString().toFloat()} Centimeter sqr"
                            // To Square feet
                            2 -> outputText.text = "${ inputText.text.toString().toFloat() / 10000/10000}"+" Hectare"
                        }
                    }
                    2 ->
                        // From Hactare
                        when(toValue.selectedItemPosition){
                            // To Meter Square
                            0 -> outputText.text = "${ inputText.text.toString().toFloat()*10000} Meter sqr"
                            // To Centimeter Square
                            1 -> outputText.text = "${ inputText.text.toString().toFloat()*10000*10000} Centimeter sqr"
                            //To Square feet
                            2 -> outputText.text = "${ inputText.text.toString().toFloat()}"+" Hectare"
                        }
                }
            }
            outputText.visibility = View.VISIBLE
        }
    }
}