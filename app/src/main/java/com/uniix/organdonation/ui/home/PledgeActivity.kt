package com.uniix.organdonation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.ActivityPledgeBinding

class PledgeActivity : Fragment() {
    lateinit var PledgeActivity : ActivityPledgeBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        PledgeActivity= ActivityPledgeBinding.inflate(inflater)

        
        return PledgeActivity.root
    }
}