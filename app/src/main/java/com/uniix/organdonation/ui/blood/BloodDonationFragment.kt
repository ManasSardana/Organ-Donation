package com.uniix.organdonation.ui.blood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentBloodDonationBinding

class BloodDonationFragment : Fragment(){
    private lateinit var bloodDonationFragment : FragmentBloodDonationBinding
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        bloodDonationFragment = FragmentBloodDonationBinding.inflate(inflater)



        return bloodDonationFragment.root
    }
}