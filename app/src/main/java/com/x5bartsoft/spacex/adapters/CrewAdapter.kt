package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutCapsuleRowBinding
import com.x5bartsoft.spacex.databinding.LayoutCoreRowBinding
import com.x5bartsoft.spacex.databinding.LayoutCrewRowBinding
import com.x5bartsoft.spacex.model.response.launchdetail.Capsule
import com.x5bartsoft.spacex.model.response.launchdetail.Cores
import com.x5bartsoft.spacex.model.response.launchdetail.Crew
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class CrewAdapter: RecyclerView.Adapter<CrewAdapter.MyViewHolder>() {

    private var crews = emptyList<Crew>()


    class MyViewHolder(private var binding: LayoutCrewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(crew: Crew) {
            binding.crew = crew
            binding.executePendingBindings()
//            binding.notifyChange()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCrewRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentCapsule = crews[position]
        holder.bind(curentCapsule)
    }

    override fun getItemCount(): Int {
        return crews.size
    }

    fun setData(newData: List<Crew>) {
        val launchDiffUtil = LaunchDiffUtil(crews, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        crews = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}