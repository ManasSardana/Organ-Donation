package com.uniix.organdonation.ui.hospitals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentHospitalsBinding

class FragmentHospitals : Fragment(){
    lateinit var fragmentHospitals : FragmentHospitalsBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        fragmentHospitals = FragmentHospitalsBinding.inflate(inflater)


        return fragmentHospitals.root
    }
}