package com.example.amonict2.helper

import android.widget.EditText
import android.widget.TextView

var EditText.textJD: String
    get() = this.text.toString()
    set(value) {this.setText(value)}

var TextView.textJD: String
    get() = this.text.toString()
    set(value) {this.setText(value)}