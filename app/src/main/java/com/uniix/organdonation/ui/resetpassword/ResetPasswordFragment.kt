package com.uniix.organdonation.ui.resetpassword

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.uniix.organdonation.MainActivity
import com.uniix.organdonation.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    //Initializing Variables
    private lateinit var resetPasswordFragment: FragmentResetPasswordBinding

    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        resetPasswordFragment = FragmentResetPasswordBinding.inflate(layoutInflater)

        resetPasswordFragment.resetNewPassword.setOnClickListener {
            val oldPassword = resetPasswordFragment.oldPassword.text.toString()
            val newPassword = resetPasswordFragment.newPassword.text.toString()
            val confirmNewPassword = resetPasswordFragment.confirmNewPassword.text.toString()
            if (oldPassword.trim().isNotEmpty() && newPassword.trim().isNotEmpty()
                && confirmNewPassword.trim().isNotEmpty()
            ) {
                if (oldPassword != newPassword) {
                    if (newPassword == confirmNewPassword) {
                        val user = auth.currentUser!!
                        val credential = EmailAuthProvider
                            .getCredential(user.email.toString(), oldPassword)

                        user.reauthenticate(credential)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Snackbar.make(
                                        resetPasswordFragment.root,
                                        "Re-authentication Successful !!",
                                        Snackbar.LENGTH_LONG
                                    ).show()

                                    user.updatePassword(confirmNewPassword)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Snackbar.make(
                                                    resetPasswordFragment.root,
                                                    "Password Reset Successful!, " +
                                                            "\nNow you can login with your new password.",
                                                    Snackbar.LENGTH_LONG
                                                ).show()
                                                auth.signOut()
                                                activity?.startActivity(
                                                    Intent(
                                                        context,
                                                        MainActivity::class.java
                                                    )
                                                )
                                            } else {
                                                Snackbar.make(
                                                    resetPasswordFragment.root,
                                                    "Password Reset Failed !!",
                                                    Snackbar.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                } else {
                                    Snackbar.make(
                                        resetPasswordFragment.root,
                                        "Re-authentication Failed !!",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                }
                            }

                    } else {
                        Snackbar.make(
                            resetPasswordFragment.root,
                            "Password Mismatch !!",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        resetPasswordFragment.root,
                        "You cannot keep new password same as old password !!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            } else {
                Snackbar.make(
                    resetPasswordFragment.root,
                    "Please enter the credentials !!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        return resetPasswordFragment.root

    }
}