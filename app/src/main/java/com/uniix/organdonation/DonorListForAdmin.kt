package com.uniix.organdonation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.uniix.organdonation.adapter.DonorDetailsAdapter
import com.uniix.organdonation.databinding.ActivityDonorListForAdminBinding
import com.uniix.organdonation.model.ProfileModel
import org.json.JSONArray
import org.json.JSONObject

class DonorListForAdmin : AppCompatActivity() {

    //Initializing Variables
    private lateinit var donorListForAdmin: ActivityDonorListForAdminBinding
    private var data = arrayListOf<ProfileModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        donorListForAdmin = ActivityDonorListForAdminBinding.inflate(layoutInflater)
        setContentView(donorListForAdmin.root)
        fetchData()
    }

    private fun fetchData() {
        val request: RequestQueue = Volley.newRequestQueue(this)  //url

        val stringRequest: StringRequest = StringRequest(
            Request.Method.GET,"https://shopwithankit.000webhostapp.com/OrgDo/fetch_data.php", {
                val profileArray: JSONArray = JSONArray(it)
                for (i in 0 until profileArray.length()) {
                    val userDetails: JSONObject = profileArray.get(i) as JSONObject
                    val profileModel = ProfileModel("${i+1}"
                        , (userDetails.getString("name").toString())
                        , (userDetails.getString("dob").toString())
                        , (userDetails.getString("bloodgroup").toString())
                        , (userDetails.getString("gender").toString())
                        , (userDetails.getString("bodypart").toString())
                        , (userDetails.getString("citypincode").toString())
                        , (userDetails.getString("email").toString())
                        , (userDetails.getString("phonenumber").toString()))
                    data.add(profileModel)
                }
                //Log.d("Data", data[0].name)
                donorListForAdmin.donorList.layoutManager = LinearLayoutManager(this)
                donorListForAdmin.donorList.adapter = DonorDetailsAdapter(data)

            }, {
                //Log.d("Error",it.toString())
                if( it.networkResponse.statusCode == 404) {
                    fetchData()
                }
            })
        request.add(stringRequest)
    }

}