package com.uniix.organdonation.ui.organ

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentOrganBinding

class FragmentOrgan : Fragment(){
    lateinit var FragmentOrgan : FragmentOrganBinding
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        FragmentOrgan = FragmentOrganBinding.inflate(inflater)

        return FragmentOrgan.root
    }
}