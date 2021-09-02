package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.ChipItemBinding
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class RocketBottomSheetAdapter : RecyclerView.Adapter<RocketBottomSheetAdapter.MyViewHolder>() {

    private var items = emptyList<String>()
    class MyViewHolder(private val binding: ChipItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String) {
            binding.chItemChip.text = name
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ChipItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentLaunch = items[position]
        holder.bind(currentLaunch)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(newData: List<String>) {
        val launchDiffUtil = LaunchDiffUtil(items, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        items = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}