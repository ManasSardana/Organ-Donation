package com.uniix.organdonation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.ActivityRegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    //Initializing Variables
    private lateinit var registrationFragment: ActivityRegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registrationFragment = ActivityRegistrationFragmentBinding.inflate(inflater)

        //Loading Login Fragment
        registrationFragment.signUpLogin.setOnClickListener {
            (activity as MainActivity).change(LoginFragment())
        }

        return registrationFragment.root

    }

}