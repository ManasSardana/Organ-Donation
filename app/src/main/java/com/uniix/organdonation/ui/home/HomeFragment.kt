package com.uniix.organdonation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.LoginFragment
import com.uniix.organdonation.MainActivity
import com.uniix.organdonation.MainPage
import com.uniix.organdonation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    //Initializing Variables
    private lateinit var homeFragment: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeFragment = FragmentHomeBinding.inflate(inflater)

        homeFragment.textView5.setOnClickListener {
            (activity as MainPage).change(PledgeActivity())
        }
        return homeFragment.root

    }

}