package com.x5bartsoft.spacex.bindingadapters

import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.x5bartsoft.spacex.model.response.launches.Doc
import com.x5bartsoft.spacex.ui.fragments.CoresFragmentDirections
import com.x5bartsoft.spacex.ui.fragments.LaunchesFragmentDirections
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.lang.Exception

class LaunchBinding {
    companion object{

        @BindingAdapter("getLaunchesFlightDate")
        @JvmStatic
        fun getLaunchesFlightDate(view: TextView, data: String) {
            val dateConvert = Instant.parse(data).toLocalDateTime(TimeZone.UTC)
            val date = dateConvert.toString().substringBeforeLast("T")
            val text = "Flight date: $date"
            view.text = text

        }

        @BindingAdapter("getLaunchesFlightNumber")
        @JvmStatic
        fun getLaunchesFlightNumber(view: TextView, data: Int) {
            val text = "Flight number: $data"
            view.text = text

        }

        @BindingAdapter("onRLaunchesClickListener")
        @JvmStatic
        fun onRLaunchesClickListener(layout: ConstraintLayout, result: Doc) {
            Log.d("onLaunchClickListener", "Called")
            layout.setOnClickListener {
                try {
                    val action =
                        CoresFragmentDirections.actionCoresFragmentToDetailActivity2(result)
                    layout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onLaunchClickListener", e.toString())
                }
            }
        }
    }
}