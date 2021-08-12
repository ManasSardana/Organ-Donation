package com.uniix.organdonation.ui.pledge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.uniix.organdonation.MainPage
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentPledgeBinding
import com.uniix.organdonation.ui.help.HelpInstructionFragment
import com.uniix.organdonation.ui.home.HomeFragment

class PledgeFragment : Fragment() {
    private lateinit var pledgeFragment : FragmentPledgeBinding
    //Heterogeneous Array
    private var bloodGroupOptions = arrayListOf<Any>("A-", "A+", "B-", "B+", "AB-", "AB+", "O-", "O+")
    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth

    private lateinit var name: String
    private lateinit var dob: String
    var bloodGroup = "None"
    private lateinit var gender: String
    private lateinit var bodyPart: String
    private lateinit var address: String
    private lateinit var cityPincode: String
    private lateinit var email: String
    private lateinit var phoneNumber: String

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        pledgeFragment= FragmentPledgeBinding.inflate(inflater)

        val bloodGroupAdapter =
            context?.let {ArrayAdapter(it, R.layout.spinner_list, bloodGroupOptions)} as SpinnerAdapter
        //ageDataAdapter.setDropDownViewResource(R.layout.spinner_list)
        pledgeFragment.bloodGroup.adapter = bloodGroupAdapter

        pledgeFragment.bloodGroup.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    bloodGroup = bloodGroupOptions[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        pledgeFragment.instructionsAndRules.setOnClickListener {
            (activity as MainPage).change(HelpInstructionFragment())
        }

        pledgeFragment.submit.setOnClickListener {
            name = pledgeFragment.pledgeName.text.toString()
            dob = pledgeFragment.pledgeDob.text.toString()

            gender = if(pledgeFragment.pledgeMale.isChecked) {
                "Male"
            } else if(pledgeFragment.pledgeFemale.isChecked) {
                "Female"
            } else {
                "Other"
            }

            bodyPart = if(pledgeFragment.allParts.isChecked){
                "All Body Parts"
            } else {
                someBodyParts()
            }

            address = pledgeFragment.pledgeAddress.text.toString()
            cityPincode = pledgeFragment.pledgeCityPincode.text.toString()
            email = pledgeFragment.pledgeEmail.text.toString()
            phoneNumber = pledgeFragment.pledgePhoneNumber.text.toString()

            if(name.trim().isNotEmpty() && dob.trim().isNotEmpty() && address.trim().isNotEmpty()
                && cityPincode.trim().isNotEmpty() && email.trim().isNotEmpty() && phoneNumber.trim().isNotEmpty()) {
                    if (email == auth.currentUser!!.email) {
                        sendDataOnServer(name, dob, bloodGroup, gender, bodyPart, address, cityPincode, email, phoneNumber)
                    } else {
                        Snackbar.make(
                            pledgeFragment.root,
                            "Please use the same email with which you have registered the app",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
            } else {
                Snackbar.make(
                    pledgeFragment.root,
                    "Please enter the credentials",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }

        return pledgeFragment.root
    }

    private fun someBodyParts(): String {
        val someBodyPart : String
        var pancreas = ""
        var corneas = ""
        var kidneys = ""
        var heart = ""
        var lungs = ""
        var liver = ""

        if (pledgeFragment.checkBox1.isChecked){
            pancreas = "Pancreas"
        }
        if(pledgeFragment.checkBox2.isChecked) {
            corneas = ", Corneas"
        }
        if(pledgeFragment.checkBox3.isChecked) {
            kidneys = ", Kidneys"
        }
        if(pledgeFragment.checkBox4.isChecked) {
            heart = ", Heart"
        }
        if(pledgeFragment.checkBox5.isChecked) {
            lungs = ", Lungs"
        }
        if(pledgeFragment.checkBox6.isChecked) {
            liver = ", Liver"
        }
        someBodyPart = "$pancreas$corneas$kidneys$heart$lungs$liver"
        return someBodyPart
    }

    private fun sendDataOnServer(
        name: String,
        dob: String,
        bloodGroup: String,
        gender: String,
        bodyPart: String,
        address: String,
        cityPincode: String,
        email: String,
        phoneNumber: String
    ) {
        val requestQueue = Volley.newRequestQueue(context)

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            "https://shopwithankit.000webhostapp.com/OrgDo/send_data.php",
            Response.Listener {
                Log.d("Response", it.toString())
                Snackbar.make(pledgeFragment.root, "Submission Successful", Snackbar.LENGTH_SHORT)
                    .show()
                (activity as MainPage).change(HomeFragment())
            },
            Response.ErrorListener {
                Log.d("Server", it.toString())
                Snackbar.make(
                    pledgeFragment.root,
                    "Please Check Details",
                    Snackbar.LENGTH_SHORT
                ).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["name"] = name
                params["dob"] = dob
                params["bloodgroup"] = bloodGroup
                params["gender"] = gender
                params["bodypart"] = bodyPart.toString()
                params["address"] = address
                params["citypincode"] = cityPincode
                params["email"] = email
                params["phonenumber"] = phoneNumber
                return params
            }

        }
        requestQueue.add(stringRequest)
    }
}