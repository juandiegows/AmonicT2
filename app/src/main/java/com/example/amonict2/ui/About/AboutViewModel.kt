package com.example.amonict2.ui.About

import android.app.Application
import android.content.ContentResolver
import android.provider.Settings
import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amonict2.R
import com.example.amonict2.helper.Singleton

class AboutViewModel() : ViewModel() {

    private val _message = MutableLiveData(Singleton.App.getString(R.string.aboutEmp))
    val message: LiveData<String> = _message
    fun SendMessage(text: String) {
        _message.value = text
    }
}