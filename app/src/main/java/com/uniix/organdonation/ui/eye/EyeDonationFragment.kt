package com.uniix.organdonation.ui.eye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentEyeDonationBinding

class EyeDonationFragment : Fragment(){
    lateinit var eyeDonationFragment : FragmentEyeDonationBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        eyeDonationFragment = FragmentEyeDonationBinding.inflate(inflater)



        return eyeDonationFragment.root
    }
}