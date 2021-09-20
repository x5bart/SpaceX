package com.x5bartsoft.spacex.bindingadapters

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ViewPagerBinding {

    companion object {

        @BindingAdapter("getFlightDateVP")
        @JvmStatic
        fun getFlightDateDetails(view: TextView, date: String) {
            val dateConvert = Instant.parse(date).toLocalDateTime(TimeZone.UTC)
            val date = dateConvert.toString().substringBeforeLast("T")
            view.text = date

        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("getSuccess")
        @JvmStatic
        fun getSuccess(view: TextView, data: Boolean) {
            Log.d("DetailsBinding", "data $data")
            if (data) view.text = "Success"
            else view.text = "Failure"
        }

        @BindingAdapter("getFlightDateVP")
        @JvmStatic
        fun getFlightDate(view: TextView, date: String) {
            val dateConvert = Instant.parse(date).toLocalDateTime(TimeZone.UTC)
            val date = dateConvert.toString().substringBeforeLast("T")
            view.text = date

        }

    }
}