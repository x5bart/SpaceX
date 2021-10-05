package com.x5bartsoft.spacex.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.model.response.launchdetail.Payload

class PayloadBinding {

    companion object {

        @BindingAdapter("getPayloadsListString")
        @JvmStatic
        fun getPayloadsListString(view: TextView, data: List<String>) {
            var count = 0
            var text = ""
            for (i in data) {
                count += 1
                text += i
                if (data.size > count) text += ", "
            }
            view.text = text
        }

        @BindingAdapter("setPayloadVisibleString")
        @JvmStatic
        fun setPayloadVisibleString(view: TextView, data: String?) {
            if (data.isNullOrEmpty()) view.visibility = View.GONE
        }

        @BindingAdapter("setPayloadVisibleInt")
        @JvmStatic
        fun setPayloadVisibleInt(view: TextView, data: Int?) {
            if (data == 0) view.visibility = View.GONE
        }

        @BindingAdapter("setPayloadVisibleDouble")
        @JvmStatic
        fun setPayloadVisibleDouble(view: TextView, data: Double?) {
            if (data == 0.0) view.visibility = View.GONE
        }

        @BindingAdapter("getPayloadMass")
        @JvmStatic
        fun getPayloadMass(view: TextView, data: Payload) {
            val kg = data.massKg
            val lbs = data.massLbs
            val text = "$kg kg / $lbs lbs"
            view.text = text
        }


    }
}