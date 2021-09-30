package com.x5bartsoft.spacex.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.R
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class OverviewBinding {

    companion object {

        @BindingAdapter("getFlightNumberOv")
        @JvmStatic
        fun getFlightNumberOv(view: TextView, data: Int) {
            val text = "Flight $data"
            view.text = text
        }

        @BindingAdapter("getDateOv")
        @JvmStatic
        fun getDateOv(view: TextView, date: String?) {
            var dateConvert: LocalDateTime? = null
            var text = ""

            if (!date.isNullOrEmpty()) {
                dateConvert = Instant.parse(date).toLocalDateTime(TimeZone.UTC)
            }
            when (view.id) {
                R.id.f_overview_static_fire_textView -> {
                    if (date.isNullOrEmpty()) view.visibility = View.GONE
                    else text = "Static fire: $dateConvert"
                }
                R.id.f_overview_loval_date_textView -> {
                    text = "Local date: $dateConvert"
                }
            }

            view.text = text

        }
    }
}