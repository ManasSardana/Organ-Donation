package com.uniix.organdonation

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.uniix.organdonation.databinding.ActivityLoginFragmentBinding
import kotlin.math.log

class LoginFragment : Fragment() {

    //Initializing Variables
    private lateinit var loginFragment: ActivityLoginFragmentBinding
    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        loginFragment = ActivityLoginFragmentBinding.inflate(inflater)

        if(auth.currentUser != null && auth.currentUser!!.isEmailVerified){
            activity?.startActivity(Intent(context, MainPage:: class.java))
            activity?.finish()
        }

        //Inflating to MainActivity
        loginFragment.login.setOnClickListener {
            val email = loginFragment.loginEmail.text.toString()
            val password = loginFragment.loginPassword.text.toString()
            if (email.trim().isNotEmpty() && password.trim().isNotEmpty()) {
                login(email, password)
            } else {
                Snackbar.make(loginFragment.root,
                    "Please enter the credentials",
                    Snackbar.LENGTH_LONG).show()
            }
            //activity?.startActivity(Intent(context, MainPage::class.java))
        }

        //Loading Forgot Password Fragment
        loginFragment.forgetPassword.setOnClickListener {
            (activity as MainActivity).change(ForgotPasswordFragment())
        }

        //Loading Registration Fragment
        loginFragment.loginSignUp.setOnClickListener {
            (activity as MainActivity).change(RegistrationFragment())
        }

        return loginFragment.root

    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    if(user!!.isEmailVerified) {
                        activity?.startActivity(Intent(context, MainPage:: class.java))
                        activity?.finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(context, "Please verify the email first!!",
                            Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context, "Something went wrong, Please try again later!!",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}