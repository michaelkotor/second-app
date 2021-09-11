package com.example.secondapplication

import android.view.View
import android.widget.EditText
import android.widget.Spinner
import java.lang.IllegalArgumentException

class EventHandler {
    val textHandler: TextHandler = TextHandler();
    val CHOISE_1: String = "Count Words"
    val CHOISE_2: String = "Count Chars"

    fun handleEvent(view: View, spinner: Spinner, editText: EditText) : Int {
        val selectedItem = spinner.selectedItem
        val toProcess: String = editText.text.toString()

        if(toProcess.isEmpty() || toProcess.isBlank()) {
            throw IllegalArgumentException("Empty one")
        }
        if(selectedItem.equals(CHOISE_1)) {
            return textHandler.countWords(toProcess)
        }

        if(selectedItem.equals(CHOISE_2)) {
            return textHandler.countChars(toProcess)
        }
        throw IllegalArgumentException()
    }
}