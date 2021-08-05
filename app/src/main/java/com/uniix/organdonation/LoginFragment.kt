package com.uniix.organdonation

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
        return loginFragment.root

    }

}