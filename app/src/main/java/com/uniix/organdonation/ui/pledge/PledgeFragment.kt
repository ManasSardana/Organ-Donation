package com.uniix.organdonation.ui.pledge

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.uniix.organdonation.MainPage
import com.uniix.organdonation.R
import com.uniix.organdonation.databinding.FragmentPledgeBinding
import com.uniix.organdonation.ui.help.HelpInstructionFragment
import java.util.*
import kotlin.collections.HashMap

class PledgeFragment : Fragment() {
    private lateinit var pledgeFragment : FragmentPledgeBinding
    //Heterogeneous Array
    private var bloodGroupOptions = arrayListOf<Any>("A-", "A+", "B-", "B+", "AB-", "AB+", "O-", "O+")
    //Variable for Firebase Authentication
    private lateinit var auth: FirebaseAuth
    private val storage = FirebaseStorage.getInstance()
    //Variable to set date
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    private lateinit var name: String
    private lateinit var dob: String
    var bloodGroup = "None"
    private lateinit var gender: String
    private lateinit var bodyPart: String
    private lateinit var address: String
    private lateinit var cityPincode: String
    private lateinit var email: String
    private lateinit var phoneNumber: String
    private lateinit var userDoc:String
    private var downloadUrl: String = "Null"

    private lateinit var progressDialog: ProgressDialog

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
                    //ToDo
                }

            }

        pledgeFragment.pledgeDob.setOnClickListener {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                requireContext()
                , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                , dateSetListener
                , year, month, day)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

        }

        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val date = "$day/${month+1}/$year"
            pledgeFragment.pledgeDob.setText(date)
        }

        pledgeFragment.instructionsAndRules.setOnClickListener {
            (activity as MainPage).change(HelpInstructionFragment())
        }

        pledgeFragment.docImageText.setOnClickListener {
            checkPermissionForImage()
        }
        pledgeFragment.docImage.setOnClickListener {
            checkPermissionForImage()
        }

        pledgeFragment.submit.setOnClickListener {
            //Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            name = pledgeFragment.pledgeName.text.toString()
            dob = pledgeFragment.pledgeDob.text.toString()

            gender = when {
                pledgeFragment.pledgeMale.isChecked -> {
                    "Male"
                }
                pledgeFragment.pledgeFemale.isChecked -> {
                    "Female"
                }
                else -> {
                    "Other"
                }
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
            userDoc = downloadUrl

            if(name.trim().isNotEmpty() && dob.trim().isNotEmpty() && address.trim().isNotEmpty() && gender.isNotEmpty() && bodyPart.isNotEmpty()
                && cityPincode.trim().isNotEmpty() && email.trim().isNotEmpty() && phoneNumber.trim().isNotEmpty()) {
                    if (email == auth.currentUser!!.email) {
                        /*if (!pledgeFragment.selectParts.isChecked) {
                            if (pledgeFragment.checkBox1.isChecked or pledgeFragment.checkBox2.isChecked
                                or pledgeFragment.checkBox3.isChecked or pledgeFragment.checkBox4.isChecked
                                or pledgeFragment.checkBox5.isChecked or pledgeFragment.checkBox6.isChecked) {
                                Snackbar.make(
                                    pledgeFragment.root,
                                    "Please Select the 'OR' option to choose from selective organs !!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                                /*if(userDoc.isEmpty() || downloadUrl.isEmpty()){
                                    Snackbar.make(
                                        pledgeFragment.root,
                                        "Please Upload the Medical Certificate !!",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                            }*/
                        } else if(!pledgeFragment.allParts.isChecked){
                            Snackbar.make(
                                pledgeFragment.root,
                                "Please Select Organs !!",
                                Snackbar.LENGTH_LONG
                            ).show()
                        } else if(pledgeFragment.allParts.isChecked || pledgeFragment.selectParts.isChecked) {*/
                            Log.d("Data", "$name, $dob, $bloodGroup, $gender, $bodyPart, $address, $cityPincode, $email, $phoneNumber, $userDoc")
                            sendDataOnServer(name, dob, bloodGroup, gender, bodyPart, address, cityPincode, email, phoneNumber, userDoc)
                        //}
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
            corneas = " Corneas"
        }
        if(pledgeFragment.checkBox3.isChecked) {
            kidneys = " Kidneys"
        }
        if(pledgeFragment.checkBox4.isChecked) {
            heart = " Heart"
        }
        if(pledgeFragment.checkBox5.isChecked) {
            lungs = " Lungs"
        }
        if(pledgeFragment.checkBox6.isChecked) {
            liver = " Liver"
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
        phoneNumber: String,
        userDoc: String
    ) {
        Log.d("Data", "$name, $dob, $bloodGroup, $gender, $bodyPart, $address, $cityPincode, $email, $phoneNumber, $userDoc")
        val requestQueue = Volley.newRequestQueue(context)

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            "https://shopwithankit.000webhostapp.com/OrgDo/send_data.php",
            Response.Listener {
                Log.d("Response", it.toString())
                Snackbar.make(pledgeFragment.root, "Submission Successful", Snackbar.LENGTH_SHORT)
                    .show()
                activity?.startActivity(Intent(context, MainPage::class.java))
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
                params["bodypart"] = bodyPart
                params["address"] = address
                params["citypincode"] = cityPincode
                params["email"] = email
                params["phonenumber"] = phoneNumber
                params["medicalcertificate"] = userDoc
                return params
            }

        }
        requestQueue.add(stringRequest)
    }

    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionWrite = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(
                    permission,
                    1001
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1001
                requestPermissions(
                    permissionWrite,
                    1002
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            } else {
                pickImageFromGallery()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(
            intent,
            1000
        ) // GIVE AN INTEGER VALUE FOR IMAGE_PICK_CODE LIKE 1000
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1000) {
            data?.data?.let {
                pledgeFragment.docImage.setImageURI(it)
                startUpload(it)
            }
        }
    }

    private fun startUpload(filePath: Uri) {
        progressDialog =  createProgressDialog("Uploading Image...", false)
        progressDialog.show()
        val ref = storage.reference.child("uploads/" + auth.uid.toString())
        val uploadTask = ref.putFile(filePath)
        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation ref.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                downloadUrl = task.result.toString()
                progressDialog.dismiss()
                Toast.makeText(context, "Image Upload Successful!!", Toast.LENGTH_LONG).show()
            } else {
                // Handle failures
                Toast.makeText(context, "Something went wrong. Please try again!", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Something went wrong. Please try again!", Toast.LENGTH_LONG).show()
        }
    }

    private fun createProgressDialog(message: String, isCancelable: Boolean): ProgressDialog {
        return ProgressDialog(context).apply {
            setCancelable(isCancelable)
            setCanceledOnTouchOutside(false)
            setMessage(message)
        }
    }

}