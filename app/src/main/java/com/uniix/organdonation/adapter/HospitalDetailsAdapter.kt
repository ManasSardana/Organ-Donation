package com.uniix.organdonation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uniix.organdonation.databinding.HospitalsDetailsBinding
import com.uniix.organdonation.model.HospitalDetailsModel

class HospitalDetailsAdapter (private val data: ArrayList<HospitalDetailsModel>) : RecyclerView.Adapter<HospitalDetailsAdapter.Data>() {
    class Data(v: View) : RecyclerView.ViewHolder(v) {
        var hospitalDetailsAdapter = HospitalsDetailsBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalDetailsAdapter.Data {
        val adapter = HospitalsDetailsBinding.inflate(LayoutInflater.from(parent.context))
        return Data(adapter.root)
    }

    override fun onBindViewHolder(holder: HospitalDetailsAdapter.Data, position: Int) {
        holder.hospitalDetailsAdapter.hospitalDetailsName.text = data[position].name
        holder.hospitalDetailsAdapter.hospitalDetailsEmail.text = data[position].email
        holder.hospitalDetailsAdapter.hospitalDetailsPhoneNumber.text = data[position].phoneNumber
        holder.hospitalDetailsAdapter.hospitalDetailsDescription.text = data[position].description
        Glide.with(holder.hospitalDetailsAdapter.root).load(data[position].imageUrl)
            .into(holder.hospitalDetailsAdapter.hospitalDetailsImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}