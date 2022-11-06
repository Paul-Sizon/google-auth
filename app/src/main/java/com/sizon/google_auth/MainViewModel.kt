package com.sizon.google_auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isSignedIn = MutableLiveData<Boolean>(false)
    val isSignedIn: LiveData<Boolean> = _isSignedIn

    fun signedIn(){
        _isSignedIn.postValue(true)
    }
}