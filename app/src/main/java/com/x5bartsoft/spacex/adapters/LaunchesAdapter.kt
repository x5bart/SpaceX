package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutLaunchRowBinding
import com.x5bartsoft.spacex.model.response.launches.Doc
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class LaunchesAdapter : RecyclerView.Adapter<LaunchesAdapter.MyViewHolder>() {

   private var docs = emptyList<Doc>()


    class MyViewHolder(private var binding: LayoutLaunchRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(docs: Doc) {
            binding.result = docs
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
        val currentLaunch = docs[position]
        holder.bind(currentLaunch)
    }

    override fun getItemCount(): Int {
        return docs.size
    }

    fun setData(newData: Launches) {
        val launchDiffUtil = LaunchDiffUtil(docs, newData.docs)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        docs = newData.docs
        diffUtilResult.dispatchUpdatesTo(this)
    }

}
