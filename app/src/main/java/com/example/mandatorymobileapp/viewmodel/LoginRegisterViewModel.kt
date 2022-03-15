package com.example.mandatorymobileapp.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.mandatorymobileapp.Repository.AuthAppRepository
import com.google.firebase.auth.FirebaseUser


class LoginRegisterViewModel {
    private var authAppRepository: AuthAppRepository? = null
    private var userLiveData: MutableLiveData<FirebaseUser?>? = null

    fun LoginRegisterViewModel(application: Application) {
        authAppRepository = AuthAppRepository(application)
        userLiveData = authAppRepository!!.userLiveData
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun login(email: String?, password: String?) {
        authAppRepository!!.login(email, password)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun register(email: String?, password: String?) {
        authAppRepository!!.register(email, password)
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser?>? {
        return userLiveData
    }
}