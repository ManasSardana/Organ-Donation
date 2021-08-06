package com.uniix.organdonation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.ActivityForgotPasswordFragmentBinding

class ForgotPasswordFragment : Fragment() {

    //Initializing Variables
    lateinit var forgotPasswordFragment: ActivityForgotPasswordFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        forgotPasswordFragment = ActivityForgotPasswordFragmentBinding.inflate(inflater)

        //Loading Login Fragment
        forgotPasswordFragment.rememberPassword.setOnClickListener {
            (activity as MainActivity).change(LoginFragment())
        }

        //Loading Registration Fragment
        forgotPasswordFragment.fgSignUp.setOnClickListener {
            (activity as MainActivity).change(RegistrationFragment())
        }

        return forgotPasswordFragment.root

    }
}