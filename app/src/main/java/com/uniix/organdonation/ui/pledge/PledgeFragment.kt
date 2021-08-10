package com.uniix.organdonation.ui.pledge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentPledgeBinding

class PledgeFragment : Fragment() {
    lateinit var pledgeActivity : FragmentPledgeBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        pledgeActivity= FragmentPledgeBinding.inflate(inflater)

        
        return pledgeActivity.root
    }
}