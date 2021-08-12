package com.uniix.organdonation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    //Initializing Variables
    private lateinit var profileFragment: FragmentProfileBinding
    private var bloodGroupOptions = arrayListOf<Any>("A-", "A+", "B-", "B+", "AB-", "AB+", "O-", "O+")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileFragment = FragmentProfileBinding.inflate(inflater)

        val bloodGroupAdapter =
            context?.let { ArrayAdapter(it, R.layout.spinner_list, bloodGroupOptions) } as SpinnerAdapter
        //ageDataAdapter.setDropDownViewResource(R.layout.spinner_list)
        profileFragment.bloodSpinnerProfile.adapter = bloodGroupAdapter

        return profileFragment.root

    }

}