package com.uniix.organdonation.ui.blood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentBloodDonationBinding
import com.uniix.organdonation.databinding.FragmentHomeBinding

class BloodDonationFragment : Fragment(){
    private lateinit var BloodDonationFragment : FragmentBloodDonationBinding
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        BloodDonationFragment = FragmentBloodDonationBinding.inflate(inflater)



        return BloodDonationFragment.root
    }
}