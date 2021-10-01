package com.x5bartsoft.spacex.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.model.response.launchdetail.Rocket

class CoreBinding {
    companion object {

        @BindingAdapter("getCoreBlock")
        @JvmStatic
        fun getCoreBlock(view: TextView, data: Int) {
            val text = "Block $data"
            view.text = text

        }
    }
}