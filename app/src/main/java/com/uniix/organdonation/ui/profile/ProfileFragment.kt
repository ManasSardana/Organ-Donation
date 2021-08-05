package com.uniix.organdonation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    //Initializing Variables
    private lateinit var profileFragment: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileFragment = FragmentProfileBinding.inflate(inflater)
        return profileFragment.root

    }

}