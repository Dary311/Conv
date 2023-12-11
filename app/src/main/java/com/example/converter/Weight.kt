package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Weight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
        supportActionBar?.title = "Weight"
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
            arrayOf("Kilogram","Gram", "Tonn")
        )
        fromValue.adapter = options
        toValue.adapter = options
        options.setDropDownViewResource(R.layout.spinner_style)
        convertButton.setOnClickListener {
            if (inputText.text.isEmpty() or (inputText.text.toString() == "."))
                outputText.text = "Please enter some value"
            else{
                when(fromValue.selectedItemPosition){
                    0 ->{
                        //From Kilogram
                        when(toValue.selectedItemPosition){
                            //To Kilogram
                            0 -> outputText.text = "${ inputText.text.toString().toFloat()} Kilogram"
                            //To Gram
                            1 -> outputText.text = "${ inputText.text.toString().toFloat()*1000} Gram"
                            //Tons
                            2 -> outputText.text = "${ inputText.text.toString().toFloat()/1000}"+" Tonn"
                        }
                    }
                    1 ->{
                        //From Gram
                        when(toValue.selectedItemPosition){
                            //To Kilogram
                            0 -> outputText.text = "${ inputText.text.toString().toFloat()/1000} Kilogram"
                            //To Gram
                            1 -> outputText.text = "${ inputText.text.toString().toFloat()} Gram"
                            //Tons
                            2 -> outputText.text = "${ inputText.text.toString().toFloat()/1000/1000}"+" Tonn"
                        }
                    }
                    2 ->
                        //From Tonn
                        when(toValue.selectedItemPosition){
                            //To Kilogram
                            0 -> outputText.text = "${ inputText.text.toString().toFloat()*1000} Kilogram"
                            //To Gram
                            1 -> outputText.text = "${ inputText.text.toString().toFloat()*1000*1000} Gram"
                            //Tons
                            2 -> outputText.text = "${ inputText.text.toString().toFloat()}"+" Tonn"
                        }
                }
            }
            outputText.visibility = View.VISIBLE
        }
    }
}