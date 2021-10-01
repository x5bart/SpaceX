package com.x5bartsoft.spacex.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.model.response.launchdetail.PayloadWeight
import com.x5bartsoft.spacex.model.response.launchdetail.Rocket

class PayloadsRowBinding {
    companion object{
        @BindingAdapter("getMassToOrbit")
        @JvmStatic
        fun getMassToOrbit(view: TextView, data: PayloadWeight) {
            val kN = data.kg
            val lbf = data.lb
            val text = "$kN kg / $lbf lbs"
            view.text = text

        }
    }
}