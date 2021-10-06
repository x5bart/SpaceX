package com.x5bartsoft.spacex.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import coil.load
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.model.response.launchdetail.Landpad
import com.x5bartsoft.spacex.model.response.launchdetail.Rocket

class CoreBinding {
    companion object {

        @BindingAdapter("getCoreBlock")
        @JvmStatic
        fun getCoreBlock(view: TextView, data: Int) {
            val text = "Block $data"
            view.text = text

        }

        @BindingAdapter("setCoreVisible")
        @JvmStatic
        fun setCoreVisible(view: ConstraintLayout, data: Landpad?) {
            if (data == null) view.visibility = View.GONE
        }

        @BindingAdapter("setCoreVisibleInt")
        @JvmStatic
        fun setCoreVisibleInt(view: TextView, data: Int?) {
            if (data == null) view.visibility = View.GONE
        }


        @BindingAdapter("getCoreString")
        @JvmStatic
        fun getCoreString(view: TextView, data: String?){
            if (data!=null) {
                view.text = data
            }
        }

        @BindingAdapter("getCoreInt")
        @JvmStatic
        fun getCoreInt(view: TextView, data: Int?){
            if (data!=null) {
                view.text = data.toString()
            }
        }

        @BindingAdapter("getCoreLandpadLocality")
        @JvmStatic
        fun getCoreLandpadLocality(view: TextView, data: Landpad?){
            if (data!=null) {
                val locality = data.locality
                val region = data.region
                val text = "$locality, $region"
                view.text = text
            }
        }

        @BindingAdapter("loadCoreOneImageFromUrl")
        @JvmStatic
        fun loadCoreOneImageFromUrl(imageView: ImageView, data: Landpad?) {
            if (data!=null) {
                imageView.load(data.images.large[0]) {
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