package com.example.mandatorymobileapp.UI.fragments



import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mandatorymobileapp.R
import com.example.mandatorymobileapp.databinding.RegisterFragmentBinding
import com.example.mandatorymobileapp.model.LoginReegisterViewModel
import com.google.firebase.auth.FirebaseUser


class RegisterFragment : Fragment(R.layout.register_fragment) {
    private lateinit var binding: RegisterFragmentBinding
    private var loginReegisterViewModel: LoginReegisterViewModel = LoginReegisterViewModel()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginReegisterViewModel = ViewModelProvider(this).get(LoginReegisterViewModel::class.java)
        loginReegisterViewModel!!.getUserLiveData()?.observe(viewLifecycleOwner, object : Observer<FirebaseUser?> {
            override fun onChanged(firebaseUser: FirebaseUser?) {
                if (firebaseUser != null) {
                    displayListFragment()
                }
            }
        })

        val view = inflater.inflate(R.layout.register_fragment, container, false)
        binding = RegisterFragmentBinding.bind(view)

        binding.registerButton.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.registerTextEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(
                    binding.registerTextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = binding.registerTextEmail.text.toString().trim { it <= ' ' }
                    val password: String = binding.registerTextPassword.text.toString().trim { it <= ' ' }
                    loginReegisterViewModel.register(email, password)



                }

            }
        }

        binding.loginClickableText.setOnClickListener { displayLoginFragment() }

        return view
    }

    private fun displayLoginFragment(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
    private fun displayListFragment(){
        findNavController().navigate(R.id.action_registerFragment_to_list_Fragment)
    }

}

