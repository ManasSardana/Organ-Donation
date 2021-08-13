package com.uniix.organdonation.ui.profile

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
import com.google.firebase.auth.FirebaseAuth
import com.uniix.organdonation.databinding.FragmentProfileBinding
import org.json.JSONArray
import org.json.JSONObject

class ProfileFragment : Fragment() {

    //Initializing Variables
    private lateinit var profileFragment: FragmentProfileBinding

    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth

    private lateinit var name: String
    private lateinit var dob: String
    private lateinit var bloodGroup: String
    private lateinit var gender: String
    private lateinit var bodyPart: String
    private lateinit var address: String
    private lateinit var cityPincode: String
    private lateinit var email: String
    private lateinit var phoneNumber: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        fetchData()
        profileFragment = FragmentProfileBinding.inflate(inflater)
        return profileFragment.root

    }

    private fun fetchData() {
        val request: RequestQueue = Volley.newRequestQueue(context)  //url
        val stringRequest: StringRequest = StringRequest(
            Request.Method.GET, "https://shopwithankit.000webhostapp.com/OrgDo/fetch_data.php", {
                val profileArray: JSONArray = JSONArray(it)
                for (i in 0 until profileArray.length()) {
                    val userDetails: JSONObject = profileArray.get(i) as JSONObject
                    if (auth.currentUser!!.email == userDetails.getString("email")) {
                        profileData()
                        break
                    } else {
                        //(activity as MainPage).change(PledgeFragment())
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

    private fun profileData() {
        val request: RequestQueue = Volley.newRequestQueue(context)  //url

        val stringRequest: StringRequest = StringRequest(
            Request.Method.GET,"https://shopwithankit.000webhostapp.com/OrgDo/fetch_data.php", {
                val profileArray: JSONArray = JSONArray(it)
                for (i in 0 until profileArray.length()) {
                    val userDetails: JSONObject = profileArray.get(i) as JSONObject
                    name = userDetails.getString("name").toString()
                    dob = userDetails.getString("dob").toString()
                    bloodGroup = userDetails.getString("bloodgroup").toString()
                    gender = userDetails.getString("gender").toString()
                    bodyPart = userDetails.getString("bodypart").toString()
                    address = userDetails.getString("address").toString()
                    cityPincode = userDetails.getString("citypincode").toString()
                    email = userDetails.getString("email").toString()
                    phoneNumber = userDetails.getString("phonenumber").toString()
                }

                profileFragment.profileName.text = name
                profileFragment.profileDob.text = dob
                profileFragment.profileBloodGroup.text = bloodGroup
                profileFragment.profileGender.text = gender
                profileFragment.profileParts.text = bodyPart
                profileFragment.profileAddress.text = address
                profileFragment.profileCityPincode.text = cityPincode
                profileFragment.profileEmail.text = email
                profileFragment.profilePhoneNumber.text = phoneNumber

            }, {
                //Log.d("Error",it.toString())
                if( it.networkResponse.statusCode == 404) {
                    profileData()
                }
            })
        request.add(stringRequest)
    }

}