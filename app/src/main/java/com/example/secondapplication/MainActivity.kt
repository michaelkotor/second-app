package com.example.secondapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    val eventHandler: EventHandler = EventHandler();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadSpinner()

    }

    fun loadSpinner() {
        val spinner: Spinner = findViewById(R.id.mainSpinner);
        val items = arrayOf("Count Words", "Count Chars")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            items
        )
        spinner.adapter = adapter
    }

    fun handleClick(view: View) {
        val spinner: Spinner = findViewById(R.id.mainSpinner)
        val editText: EditText = findViewById(R.id.editTextTextMultiLine)
        try {
            val result: Int = eventHandler.handleEvent(view, spinner, editText)
            Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Text should not be blank or empty", Toast.LENGTH_SHORT).show()

        }


}

}