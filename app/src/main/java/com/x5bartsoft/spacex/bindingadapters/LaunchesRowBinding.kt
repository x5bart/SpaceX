package com.x5bartsoft.spacex.bindingadapters

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.x5bartsoft.spacex.model.response.launches.Doc
import com.x5bartsoft.spacex.ui.fragments.LaunchesFragmentDirections
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.lang.Exception

class LaunchesRowBinding {

    companion object {

        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Doc) {
            Log.d("onLaunchClickListener", "Called")
            recipeRowLayout.setOnClickListener {
                try {
                    val action =
                        LaunchesFragmentDirections.actionLaunchesFragmentToDetailActivity(result)
                    recipeRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onLaunchClickListener", e.toString())
                }
            }
        }


        @BindingAdapter("getFlightDate")
        @JvmStatic
        fun getFlightDate(view: TextView, date: String) {
            val dateConvert = Instant.parse(date).toLocalDateTime(TimeZone.UTC)
            val date = dateConvert.toString().substringBeforeLast("T")
            view.text = date

        }

        @BindingAdapter("getDetail")
        @JvmStatic
        @SuppressLint("SetTextI18n")
        fun getDetail(view: TextView, detail: String?) {
            if (detail != null) view.text = detail
            else view.text = "This launch does not contain a description."

        }
    }

}