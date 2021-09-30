package com.x5bartsoft.spacex.bindingadapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.x5bartsoft.spacex.R

class ImageBinding {

    companion object {

        @BindingAdapter("loadShipImageFromUrl")
        @JvmStatic
        fun loadShipImageFromUrl(imageView: ImageView, imageUrl: String?) {
            Log.d("LaunchesBindingMain", "imageUrl:$imageUrl")
            if (imageUrl!=null) {
                imageView.load(imageUrl) {
                    crossfade(600)
//                    placeholder(R.drawable.ic_placeholder_error)
                    error(R.drawable.ic_placeholder_error)
                }
            } else {
                imageView.load(R.drawable.ic_placeholder_error)
            }
        }

        @BindingAdapter("loadPatchImageFromUrl")
        @JvmStatic
        fun loadPatchImageFromUrl(imageView: ImageView, imageUrl: String?) {
            Log.d("LaunchesBindingPatch", "imageUrl:$imageUrl")
            if (imageUrl != null) {
                imageView.load(imageUrl) {
                    crossfade(600)
                    transformations(CircleCropTransformation())
                }
            }
        }

        @BindingAdapter("loadPatchImageFromUrlVP")
        @JvmStatic
        fun loadPatchImageFromUrlVP(imageView: ImageView, imageUrl: String?) {
            Log.d("LaunchesBindingPatch", "imageUrl:$imageUrl")
            if (imageUrl != null) {
                imageView.load(imageUrl) {
                    crossfade(600)
                }
            }
        }

        @BindingAdapter("loadMainImageFromUrl")
        @JvmStatic
        fun loadMainImageFromUrl(imageView: ImageView, imageUrl: List<String>?) {
            Log.d("LaunchesBindingMain", "imageUrl:$imageUrl")
            if (imageUrl!!.isNotEmpty()) {
                imageView.load(imageUrl[0]) {
                    crossfade(600)
//                    placeholder(R.drawable.ic_placeholder_error)
                    error(R.drawable.ic_placeholder_error)
                }
            } else {
                imageView.load(R.drawable.ic_placeholder_error)
            }
        }

        @BindingAdapter("loadMainImageForViewPager")
        @JvmStatic
        fun loadMainImageForViewPager(imageView: ImageView, imageUrl: String?) {
            Log.d("LaunchesBindingMain", "imageUrl:$imageUrl")
            if (imageUrl!!.isNotEmpty()) {
                imageView.load(imageUrl) {
                    crossfade(600)
//                    placeholder(R.drawable.ic_placeholder_error)
                    error(R.drawable.ic_placeholder_error)
                }
            } else {
                imageView.load(R.drawable.ic_placeholder_error)
            }
        }


    }
}