package com.uniix.organdonation.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentHelpInstructionBinding

class HelpInstructionFragment : Fragment() {
    //Initializing Variables
    private lateinit var helpInstructionFragment: FragmentHelpInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        helpInstructionFragment = FragmentHelpInstructionBinding.inflate(layoutInflater)
        return helpInstructionFragment.root
    }
}