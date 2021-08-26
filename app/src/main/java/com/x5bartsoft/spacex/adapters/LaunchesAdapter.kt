package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutLaunchRowBinding
import com.x5bartsoft.spacex.model.launches.Launch
import com.x5bartsoft.spacex.model.rockets.Rockets
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class LaunchesAdapter : RecyclerView.Adapter<LaunchesAdapter.MyViewHolder>() {

    var launches = emptyList<Launch>()


    class MyViewHolder(private var binding: LayoutLaunchRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(launch: Launch) {
            binding.result = launch
            binding.executePendingBindings()
//            binding.notifyChange()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutLaunchRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentLaunch = launches[position]
        holder.bind(currentLaunch)
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    fun setData(newData: List<Launch>) {
        val launchDiffUtil = LaunchDiffUtil(launches, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        launches = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}
