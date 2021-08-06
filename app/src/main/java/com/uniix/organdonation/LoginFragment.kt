package com.uniix.organdonation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.ActivityLoginFragmentBinding

class LoginFragment : Fragment() {

    //Initializing Variables
    private lateinit var loginFragment: ActivityLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginFragment = ActivityLoginFragmentBinding.inflate(inflater)

        //Inflating to MainPAge
        loginFragment.login.setOnClickListener {
            activity?.startActivity(Intent(context, MainPage::class.java))
        }

        //Loading Forgot Password Fragment
        loginFragment.forgetPassword.setOnClickListener {
            (activity as MainActivity).change(ForgotPasswordFragment())
        }

        //Loading Registration Fragment
        loginFragment.loginSignUp.setOnClickListener {
            (activity as MainActivity).change(RegistrationFragment())
        }

        return loginFragment.root

    }

}