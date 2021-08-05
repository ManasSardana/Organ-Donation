package com.uniix.organdonation.ui.donate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentDonateBinding

class DonateFragment : Fragment() {

    //Initializing Variables
    private lateinit var donateFragment: FragmentDonateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        donateFragment = FragmentDonateBinding.inflate(inflater)
        return donateFragment.root

    }

}