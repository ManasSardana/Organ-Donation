package com.uniix.organdonation.ui.blood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.uniix.organdonation.adapter.HospitalDetailsAdapter
import com.uniix.organdonation.databinding.FragmentBloodDonationBinding
import com.uniix.organdonation.model.HospitalDetailsModel
import org.json.JSONArray
import org.json.JSONObject

class BloodDonationFragment : Fragment(){
    private lateinit var bloodDonationFragment : FragmentBloodDonationBinding
    private var data = arrayListOf<HospitalDetailsModel>()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        bloodDonationFragment = FragmentBloodDonationBinding.inflate(inflater)
        fetchData()
        return bloodDonationFragment.root
    }

    private fun fetchData() {
        val request: RequestQueue = Volley.newRequestQueue(context)  //url

        val stringRequest: StringRequest = StringRequest(
            Request.Method.GET,"https://shopwithankit.000webhostapp.com/OrgDo/hospitals_data.php", {
                val hospitalArray: JSONArray = JSONArray(it)
                for (i in 0 until hospitalArray.length()) {
                    val hospitalDetails: JSONObject = hospitalArray.get(i) as JSONObject
                    val hospitalModelDetails = HospitalDetailsModel(hospitalDetails.getString("name").toString()
                        , (hospitalDetails.getString("email").toString())
                        , (hospitalDetails.getString("phonenumber").toString())
                        , (hospitalDetails.getString("description").toString())
                        , (hospitalDetails.getString("image").toString()))
                    data.add(hospitalModelDetails)
                }
                //Log.d("Data", data[0].name)
                bloodDonationFragment.bloodDonationHospitalsDetails.layoutManager = LinearLayoutManager(context)
                bloodDonationFragment.bloodDonationHospitalsDetails.adapter = HospitalDetailsAdapter(data)

            }, {
                //Log.d("Error",it.toString())
                if( it.networkResponse.statusCode == 404) {
                    fetchData()
                }
            })
        request.add(stringRequest)
    }

}