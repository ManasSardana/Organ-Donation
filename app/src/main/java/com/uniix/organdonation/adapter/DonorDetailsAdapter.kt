package com.uniix.organdonation.adapter

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uniix.organdonation.databinding.DonorDetailsBinding
import com.uniix.organdonation.model.ProfileModel

class DonorDetailsAdapter (private val data: ArrayList<ProfileModel>) : RecyclerView.Adapter<DonorDetailsAdapter.Data>() {
    class Data(v: View) : RecyclerView.ViewHolder(v) {
        var donorDetailsAdapter = DonorDetailsBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorDetailsAdapter.Data {
        val adapter = DonorDetailsBinding.inflate(LayoutInflater.from(parent.context))
        return Data(adapter.root)
    }

    override fun onBindViewHolder(holder: DonorDetailsAdapter.Data, position: Int) {
        holder.donorDetailsAdapter.id.text = data[position].id
        holder.donorDetailsAdapter.donorName.text = data[position].name
        holder.donorDetailsAdapter.donorDob.text = data[position].dob
        holder.donorDetailsAdapter.donorBloodGroup.text = data[position].bloodGroup
        holder.donorDetailsAdapter.donorGender.text = data[position].gender
        holder.donorDetailsAdapter.donorLocation.text = data[position].cityPincode
        holder.donorDetailsAdapter.donorEmail.text = data[position].email
        holder.donorDetailsAdapter.donorPhoneNumber.text = data[position].phoneNumber
        holder.donorDetailsAdapter.donorParts.text = data[position].bodyPart
        //holder.donorDetailsAdapter.donorMediCert.text = /*"Medical Certificate URL: " +*/ data[position].userDoc
        val url = data[position].userDoc
        holder.donorDetailsAdapter.donorMediCert.text = Html.fromHtml("<a href=\"$url\"> Medical Certificate </a>")
        holder.donorDetailsAdapter.donorMediCert.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun getItemCount(): Int {
        return data.size
    }

}