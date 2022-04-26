package com.example.mandatorymobileapp.model


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mandatorymobileapp.Repository.AuthAppRepository


import com.google.firebase.auth.FirebaseUser


class LoginReegisterViewModel : ViewModel() {

    private val authAppRepository = AuthAppRepository()
    private var userLiveData: MutableLiveData<FirebaseUser>? = authAppRepository.getUserLiveData()
    private var loggedOutLiveData: MutableLiveData<Boolean>? = authAppRepository.getLoggedOutLiveData()



    fun login(email: String, password: String){
        authAppRepository.login(email, password)
    }

    fun register(email: String?, password: String?) {
        authAppRepository.register(email!!, password!!)
    }

    fun logOut() {
        authAppRepository.logOut()
    }


    fun getLoggedOutLiveData(): MutableLiveData<Boolean>? {
        return loggedOutLiveData
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser>? {
        return userLiveData
    }

}