package com.x5bartsoft.spacex.bindingadapters

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
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

        @BindingAdapter("getOverviewLink")
        @JvmStatic
        fun getOverviewLink(view: ImageView, date: String?) {
            if (date != null) {
                view.alpha = 1.0F
                view.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(date)
                    view.context.startActivity(intent)
                }
            }
        }
    }
}