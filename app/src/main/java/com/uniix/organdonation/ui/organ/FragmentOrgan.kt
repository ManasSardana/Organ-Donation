package com.uniix.organdonation.ui.organ

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentOrganBinding

class FragmentOrgan : Fragment(){
    lateinit var fragmentOrgan : FragmentOrganBinding
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        fragmentOrgan = FragmentOrganBinding.inflate(inflater)

        return fragmentOrgan.root
    }
}