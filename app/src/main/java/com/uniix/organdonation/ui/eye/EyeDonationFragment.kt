package com.uniix.organdonation.ui.eye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentEyeDonationBinding

class EyeDonationFragment : Fragment(){
    lateinit var EyeDonationFragment : FragmentEyeDonationBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        EyeDonationFragment = FragmentEyeDonationBinding.inflate(inflater)



        return EyeDonationFragment.root
    }
}