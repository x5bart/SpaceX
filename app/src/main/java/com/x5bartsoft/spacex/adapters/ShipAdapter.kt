package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.databinding.LayoutShipRowBinding
import com.x5bartsoft.spacex.model.response.launchdetail.Ship
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class ShipAdapter : RecyclerView.Adapter<ShipAdapter.MyViewHolder>() {

    private var ships = emptyList<Ship>()


    class MyViewHolder(private var binding: LayoutShipRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ship: Ship) {
            binding.ship = ship
            binding.executePendingBindings()
//            binding.notifyChange()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutShipRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentShip = ships[position]
        holder.bind(currentShip)
    }

    override fun getItemCount(): Int {
        return ships.size
    }

    fun setData(newData: List<Ship>) {
        val launchDiffUtil = LaunchDiffUtil(ships, newData)
        val diffUtilResult = DiffUtil.calculateDiff(launchDiffUtil)
        ships = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}