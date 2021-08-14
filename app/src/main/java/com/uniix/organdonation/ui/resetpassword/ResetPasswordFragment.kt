package com.uniix.organdonation.ui.resetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uniix.organdonation.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    //Initializing Variables
    private lateinit var resetPasswordFragment: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resetPasswordFragment = FragmentResetPasswordBinding.inflate(layoutInflater)

        return resetPasswordFragment.root
    }
}