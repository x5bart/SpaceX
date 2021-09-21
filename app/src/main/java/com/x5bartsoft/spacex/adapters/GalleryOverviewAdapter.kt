package com.x5bartsoft.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.x5bartsoft.spacex.databinding.LayoutOverviewGalleryItemBinding
import com.x5bartsoft.spacex.model.ImageViewPager
import com.x5bartsoft.spacex.util.LaunchDiffUtil

class GalleryOverviewAdapter : RecyclerView.Adapter<GalleryOverviewAdapter.MyViewHolder>() {

    private var imageList = arrayListOf<ImageViewPager>()
    private lateinit var viewPager: ViewPager2

    class MyViewHolder(private var binding: LayoutOverviewGalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imageView = binding.lGalleryItemGalleryImageView

        fun bind(currentImage: ImageViewPager) {
            binding.detail = currentImage
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutOverviewGalleryItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentImage = imageList[position]
        holder.bind(currentImage)

    }

    override fun getItemCount(): Int {
        return imageList.size
    }


    fun setData(newData: ArrayList<ImageViewPager>, viewPager2: ViewPager2) {
        viewPager = viewPager2
        val galleryDiffUtil = LaunchDiffUtil(imageList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(galleryDiffUtil)
        imageList = newData
        diffUtilResult.dispatchUpdatesTo(this)

    }
}