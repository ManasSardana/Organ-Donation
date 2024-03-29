package com.uniix.organdonation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.uniix.organdonation.databinding.ActivityForgotPasswordFragmentBinding

class ForgotPasswordFragment : Fragment() {

    //Initializing Variables
    private lateinit var forgotPasswordFragment: ActivityForgotPasswordFragmentBinding
    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        forgotPasswordFragment = ActivityForgotPasswordFragmentBinding.inflate(inflater)

        //Sending Password reset email
        forgotPasswordFragment.fgPasswordLink.setOnClickListener {
            val email = forgotPasswordFragment.fgEmail.text.toString()
            if (email.trim().isNotEmpty()){
                val eMail = forgotPasswordFragment.fgEmail.text.toString()
                //auth = Firebase.auth
                auth.sendPasswordResetEmail(eMail)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Snackbar.make(forgotPasswordFragment.root,
                                "Password Reset Link Send to your Email !!",
                                Snackbar.LENGTH_LONG).show()
                        } else {
                            if (task.exception is FirebaseAuthInvalidCredentialsException ||
                                task.exception is FirebaseAuthInvalidUserException){
                                Snackbar.make(forgotPasswordFragment.root,
                                    "Invalid Credentials !!",
                                    Snackbar.LENGTH_LONG).show()
                            } else {
                                Snackbar.make(forgotPasswordFragment.root,
                                    "Something went wrong, Please try again later !!",
                                    Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
            } else {
                Snackbar.make(forgotPasswordFragment.root,
                    "Please enter the credentials !!",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        //Loading Login Fragment
        forgotPasswordFragment.rememberPassword.setOnClickListener {
            (activity as MainActivity).change(LoginFragment())
        }

        //Loading Registration Fragment
        forgotPasswordFragment.fgSignUp.setOnClickListener {
            (activity as MainActivity).change(RegistrationFragment())
        }

        return forgotPasswordFragment.root

    }

}