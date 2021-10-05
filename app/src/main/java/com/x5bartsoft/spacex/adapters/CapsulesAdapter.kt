package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutCapsuleRowBinding
import com.x5bartsoft.spacex.databinding.LayoutCoreRowBinding
import com.x5bartsoft.spacex.model.response.launchdetail.Capsule
import com.x5bartsoft.spacex.model.response.launchdetail.Cores
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class CapsulesAdapter: RecyclerView.Adapter<CapsulesAdapter.MyViewHolder>() {

    private var capsules = emptyList<Capsule>()


    class MyViewHolder(private var binding: LayoutCapsuleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(capsule: Capsule ) {
            binding.capsule = capsule
            binding.executePendingBindings()
//            binding.notifyChange()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCapsuleRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentCapsule = capsules[position]
        holder.bind(curentCapsule)
    }

    override fun getItemCount(): Int {
        return capsules.size
    }

    fun setData(newData: List<Capsule>) {
        val launchDiffUtil = LaunchDiffUtil(capsules, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        capsules = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}