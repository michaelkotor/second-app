package com.example.secondapplication

class TextHandler() {
    init {
        System.loadLibrary("secondapplication")
    }

    external fun countChars(text: String) : Int
    external fun countWords(text: String) : Int

}