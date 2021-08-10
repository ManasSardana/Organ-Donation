package com.uniix.organdonation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.MainPage
import com.uniix.organdonation.databinding.FragmentHomeBinding
import com.uniix.organdonation.ui.blood.BloodDonationFragment
import com.uniix.organdonation.ui.eye.EyeDonationFragment
import com.uniix.organdonation.ui.pledge.PledgeFragment

class HomeFragment : Fragment() {

    //Initializing Variables
    private lateinit var homeFragment: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        homeFragment = FragmentHomeBinding.inflate(inflater)

        homeFragment.textView5.setOnClickListener {
            (activity as MainPage).change(PledgeFragment())
        }

        homeFragment.imageView5.setOnClickListener {
            (activity as MainPage).change(PledgeFragment())
        }

        homeFragment.textView3.setOnClickListener {
            (activity as MainPage).change(BloodDonationFragment())
        }

        homeFragment.imageView3.setOnClickListener {
            (activity as MainPage).change(BloodDonationFragment())
        }

        homeFragment.textView4.setOnClickListener {
            (activity as MainPage).change(EyeDonationFragment())
        }

        homeFragment.textView4.setOnClickListener {
            (activity as MainPage).change(EyeDonationFragment())
        }



        return homeFragment.root

    }

}