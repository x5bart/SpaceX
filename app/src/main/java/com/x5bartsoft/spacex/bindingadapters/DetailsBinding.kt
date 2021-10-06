package com.x5bartsoft.spacex.bindingadapters

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.model.response.launchdetail.*

class DetailsBinding {


    companion object {



        @BindingAdapter("getLinkReddit")
        @JvmStatic
        fun getLinkReddit(view: ImageView, date: Reddit) {
            if (date.campaign != null || date.launch != null || date.media != null || date.recovery != null) {
                view.alpha = 1.0F
            }
        }

        @BindingAdapter("getLink")
        @JvmStatic
        fun getLink(view: ImageView, date: String?) {
            if (date != null) {
                view.alpha = 1.0F
            }
        }



        @BindingAdapter("getDetails")
        @JvmStatic
        @SuppressLint("SetTextI18n")
        fun getDetails(view: TextView, detail: String?) {
            if (detail != null) view.text = detail
            else view.text = "This launch does not contain a description."

        }


    }
}