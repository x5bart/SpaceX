package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutMassToOrbitRowBinding
import com.x5bartsoft.spacex.model.response.launchdetail.PayloadWeight
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class MassToOrbitAdapter :RecyclerView.Adapter<MassToOrbitAdapter.MyViewHolder>() {

    private var payloads = emptyList<PayloadWeight>()


    class MyViewHolder(private var binding: LayoutMassToOrbitRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(payloadWeight: PayloadWeight) {
            binding.payload = payloadWeight
            binding.executePendingBindings()
//            binding.notifyChange()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutMassToOrbitRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentPayload = payloads[position]
        holder.bind(curentPayload)
    }

    override fun getItemCount(): Int {
        return payloads.size
    }

    fun setData(newData: List<PayloadWeight>) {
        val launchDiffUtil = LaunchDiffUtil(payloads, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        payloads = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}