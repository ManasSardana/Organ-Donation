package com.uniix.organdonation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.uniix.organdonation.MainPage
import com.uniix.organdonation.databinding.FragmentHomeBinding
import com.uniix.organdonation.ui.blood.BloodDonationFragment
import com.uniix.organdonation.ui.eye.EyeDonationFragment
import com.uniix.organdonation.ui.hospitals.FragmentHospitals
import com.uniix.organdonation.ui.organ.FragmentOrgan
import com.uniix.organdonation.ui.pledge.PledgeFragment
import org.json.JSONArray
import org.json.JSONObject

class HomeFragment : Fragment() {

    //Initializing Variables
    private lateinit var homeFragment: FragmentHomeBinding

    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        homeFragment = FragmentHomeBinding.inflate(inflater)

        homeFragment.pledge.setOnClickListener {
            pledge()
        }

        homeFragment.pledgeImage.setOnClickListener {
            pledge()
        }

        homeFragment.blood.setOnClickListener {
            (activity as MainPage).change(BloodDonationFragment())
        }

        homeFragment.bloodImage.setOnClickListener {
            (activity as MainPage).change(BloodDonationFragment())
        }

        homeFragment.eye.setOnClickListener {
            (activity as MainPage).change(EyeDonationFragment())
        }

        homeFragment.eyeImage.setOnClickListener {
            (activity as MainPage).change(EyeDonationFragment())
        }

        homeFragment.organ.setOnClickListener {
            (activity as MainPage).change(FragmentOrgan())
        }

        homeFragment.organImage.setOnClickListener {
            (activity as MainPage).change(FragmentOrgan())
        }

        homeFragment.hospitals.setOnClickListener {
            (activity as MainPage).change(FragmentHospitals())
        }

        homeFragment.hospitalImage.setOnClickListener {
            (activity as MainPage).change(FragmentHospitals())
        }

        return homeFragment.root

    }

    private fun pledge() {
        val request: RequestQueue = Volley.newRequestQueue(context)  //url
        val stringRequest: StringRequest = StringRequest(
            Request.Method.GET, "https://shopwithankit.000webhostapp.com/OrgDo/fetch_data.php", {
                val profileArray: JSONArray = JSONArray(it)
                for (i in 0 until profileArray.length()) {
                    val userDetails: JSONObject = profileArray.get(i) as JSONObject
                    if (auth.currentUser!!.email != userDetails.getString("email")) {
                        (activity as MainPage).change(PledgeFragment())
                    } else {
                        Toast.makeText(context, "Hurray!, You have already taken the pledge!",
                            Toast.LENGTH_LONG).show()
                        break
                    }
                }
            }, {
                //Log.d("Error",it.toString())
                if (it.networkResponse.statusCode == 404) {
                    Toast.makeText(context, "Please try again later !!", Toast.LENGTH_LONG).show()
                }
            })
        request.add(stringRequest)
    }
}