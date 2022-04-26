package com.example.mandatorymobileapp.Repository


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthAppRepository{
    private val application: Application = Application()
    private  val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userLiveData: MutableLiveData<FirebaseUser> = MutableLiveData<FirebaseUser>()
    private var loggedOutLiveData: MutableLiveData<Boolean> =  MutableLiveData<Boolean>()



    fun login(email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(
            {task ->
                if (task.isSuccessful) {
                    userLiveData.postValue(firebaseAuth.currentUser);
                }
            })
    }

        fun register(email: String, password: String) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                {task ->
                    if (task.isSuccessful){
                        userLiveData.postValue(firebaseAuth.currentUser)
                    }


                })
        }

    fun logOut() {
        firebaseAuth.signOut()
        loggedOutLiveData.postValue(true)
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser>? {
        return userLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<Boolean>? {
        return loggedOutLiveData
    }
    }