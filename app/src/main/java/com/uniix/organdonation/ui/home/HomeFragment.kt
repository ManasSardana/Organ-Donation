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
import com.uniix.organdonation.ui.hospitals.FragmentHospitals
import com.uniix.organdonation.ui.organ.FragmentOrgan
import com.uniix.organdonation.ui.pledge.PledgeFragment

class HomeFragment : Fragment() {

    //Initializing Variables
    private lateinit var homeFragment: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        homeFragment = FragmentHomeBinding.inflate(inflater)

        homeFragment.pledge.setOnClickListener {
            (activity as MainPage).change(PledgeFragment())
        }

        homeFragment.pledgeImage.setOnClickListener {
            (activity as MainPage).change(PledgeFragment())
        }

        homeFragment.blood.setOnClickListener {
            (activity as MainPage).change(BloodDonationFragment())
        }

        homeFragment.bloodImage.setOnClickListener {
            (activity as MainPage).change(BloodDonationFragment())
        }

        homeFragment.eye.setOnClickListener {
            (activity as MainPage).change(EyeDonationFragment())
        }

        homeFragment.eyeImage.setOnClickListener {
            (activity as MainPage).change(EyeDonationFragment())
        }

        homeFragment.organ.setOnClickListener {
            (activity as MainPage).change(FragmentOrgan())
        }

        homeFragment.organImage.setOnClickListener {
            (activity as MainPage).change(FragmentOrgan())
        }

        homeFragment.hospitals.setOnClickListener {
            (activity as MainPage).change(FragmentHospitals())
        }

        homeFragment.hospitalImage.setOnClickListener {
            (activity as MainPage).change(FragmentHospitals())
        }

        return homeFragment.root

    }

}