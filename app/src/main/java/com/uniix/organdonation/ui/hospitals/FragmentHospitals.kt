package com.uniix.organdonation.ui.hospitals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentHospitalsBinding

class FragmentHospitals : Fragment(){
    lateinit var FragmentHospitals : FragmentHospitalsBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        FragmentHospitals = FragmentHospitalsBinding.inflate(inflater)


        return FragmentHospitals.root
    }
}