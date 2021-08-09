package com.uniix.organdonation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.MainPage
import com.uniix.organdonation.databinding.FragmentHomeBinding
import com.uniix.organdonation.ui.pledge.PledgeFragment

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
            (activity as MainPage).change(PledgeFragment())
        }
        return homeFragment.root

    }

}