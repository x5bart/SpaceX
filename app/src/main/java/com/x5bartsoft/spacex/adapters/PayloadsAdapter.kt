package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutCoreRowBinding
import com.x5bartsoft.spacex.databinding.LayoutPayloadRowBinding
import com.x5bartsoft.spacex.model.response.launchdetail.Core
import com.x5bartsoft.spacex.model.response.launchdetail.Cores
import com.x5bartsoft.spacex.model.response.launchdetail.Payload
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class PayloadsAdapter: RecyclerView.Adapter<PayloadsAdapter.MyViewHolder>() {

    private var payloads = emptyList<Payload>()


    class MyViewHolder(private var binding: LayoutPayloadRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(payload: Payload ) {
            binding.payload = payload
            binding.executePendingBindings()
//            binding.notifyChange()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutPayloadRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentCore = payloads[position]
        holder.bind(curentCore)
    }

    override fun getItemCount(): Int {
        return payloads.size
    }

    fun setData(newData: List<Payload>) {
        val launchDiffUtil = LaunchDiffUtil(payloads, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        payloads = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}