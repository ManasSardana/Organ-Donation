package com.uniix.organdonation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.uniix.organdonation.databinding.ActivityRegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    //Initializing Variables
    private lateinit var registrationFragment: ActivityRegistrationFragmentBinding
    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        registrationFragment = ActivityRegistrationFragmentBinding.inflate(inflater)

        //Registering the user
        registrationFragment.signUp.setOnClickListener {
            val email = registrationFragment.registrationEmail.text.toString()
            val password = registrationFragment.registrationPassword.text.toString()
            val confirmPassword = registrationFragment.registrationConfirmPassword.text.toString()
            if(email.trim().isNotEmpty() && password.trim().isNotEmpty()
                && confirmPassword.trim().isNotEmpty()){
                    if(password == confirmPassword){
                        createAccount(email, confirmPassword)
                    } else {
                        Snackbar.make(registrationFragment.root,
                            "Password Mismatch !!",
                            Snackbar.LENGTH_LONG).show()
                    }
            } else {
                Snackbar.make(registrationFragment.root,
                    "Please enter the credentials !!",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        //Loading Login Fragment
        registrationFragment.signUpLogin.setOnClickListener {
            (activity as MainActivity).change(LoginFragment())
        }

        return registrationFragment.root

    }

    private fun createAccount(email: String, confirmPassword: String) {
        auth.createUserWithEmailAndPassword(email, confirmPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                user!!.sendEmailVerification()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context,
                                "Please Check Your Email for verification to Complete Your Registration.",
                                Toast.LENGTH_LONG).show()
                            (activity as MainActivity).change(LoginFragment())
                        } else {
                            // If sign in fails, display a message to the user
                            if (task.exception is FirebaseAuthUserCollisionException) {
                                Toast.makeText(context, "User already registered.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            } else {
                // If sign in fails, display a message to the user
                if (task.exception is FirebaseAuthWeakPasswordException){
                    Toast.makeText(context, "Weak Password. Please use both alphabet and number !!"
                        , Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}