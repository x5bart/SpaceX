package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutCoreRowBinding
import com.x5bartsoft.spacex.model.response.launchdetail.Core
import com.x5bartsoft.spacex.model.response.launchdetail.Cores
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class CoresAdapter: RecyclerView.Adapter<CoresAdapter.MyViewHolder>() {

    private var cores = emptyList<Cores>()


    class MyViewHolder(private var binding: LayoutCoreRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cores: Cores ) {
            binding.cores = cores
            binding.executePendingBindings()
//            binding.notifyChange()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCoreRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentCore = cores[position]
        holder.bind(curentCore)
    }

    override fun getItemCount(): Int {
        return cores.size
    }

    fun setData(newData: List<Cores>) {
        val launchDiffUtil = LaunchDiffUtil(cores, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        cores = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}