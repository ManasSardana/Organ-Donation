package com.uniix.organdonation.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    //Initializing Variables
    private lateinit var aboutUsFragment: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        aboutUsFragment = FragmentAboutUsBinding.inflate(inflater)
        return aboutUsFragment.root

    }

}