package com.example.mandatorymobileapp.UI.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mandatorymobileapp.R
import com.example.mandatorymobileapp.databinding.LoginFragmentBinding
import com.example.mandatorymobileapp.databinding.RegisterFragmentBinding
import com.example.mandatorymobileapp.model.LoginReegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.Console


class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private var loginRegisterViewModel: LoginReegisterViewModel = LoginReegisterViewModel()
    var firebaseUser : FirebaseUser? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.login_fragment, container, false)
        binding = LoginFragmentBinding.bind(view)


        loginRegisterViewModel = ViewModelProvider(this).get(LoginReegisterViewModel::class.java)
        loginRegisterViewModel.getUserLiveData()?.observe(viewLifecycleOwner, object :
            Observer<FirebaseUser?> {
            override fun onChanged(firebaseUser: FirebaseUser?) {
                if (firebaseUser != null) {
                    displayListFragment()
                }
            }
        })


        binding.registerClickableText.setOnClickListener {
            displayRegisterFragment()
        }

        binding.Loginbutton.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.LoginTextEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(
                    binding.LoginTextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = binding.LoginTextEmail.text.toString().trim { it <= ' ' }
                    val password: String = binding.LoginTextPassword.text.toString().trim{ it <= ' ' }
                    loginRegisterViewModel.login(email, password)

                    }
                }
            }

        return view
        }

    private fun displayRegisterFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
    private fun displayListFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_list_Fragment)
    }
    }

