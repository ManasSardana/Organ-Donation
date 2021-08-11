package com.uniix.organdonation.ui.pledge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentPledgeBinding

class PledgeFragment : Fragment() {
    private lateinit var pledgeFragment : FragmentPledgeBinding
    //Heterogeneous Array
    private var bloodGroupOptions = arrayListOf<Any>("A-", "A+", "B-", "B+", "AB-", "AB+", "O-", "O+")

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        pledgeFragment= FragmentPledgeBinding.inflate(inflater)

        val bloodGroupAdapter =
            context?.let {ArrayAdapter(it, R.layout.spinner_list, bloodGroupOptions)} as SpinnerAdapter
        //ageDataAdapter.setDropDownViewResource(R.layout.spinner_list)
        pledgeFragment.bloodGroup.adapter = bloodGroupAdapter

        return pledgeFragment.root
    }
}