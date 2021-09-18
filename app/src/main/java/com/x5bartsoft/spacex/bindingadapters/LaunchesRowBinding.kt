package com.x5bartsoft.spacex.bindingadapters

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.x5bartsoft.spacex.R
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


        @BindingAdapter("loadPatchImageFromUrl")
        @JvmStatic
        fun loadPatchImageFromUrl(imageView: ImageView, imageUrl: String?) {
            if (imageUrl != null) {
                imageView.load(imageUrl) {
                    crossfade(600)
                    transformations(CircleCropTransformation())
                }
            }
        }

        @BindingAdapter("loadMainImageFromUrl")
        @JvmStatic
        fun loadMainImageFromUrl(imageView: ImageView, imageUrl: List<String>?) {
            if (imageUrl!!.isNotEmpty()) {
                    imageView.load(imageUrl[0]) {
                        crossfade(600)
                        placeholder(R.drawable.ic_placeholder_error)
                        error(R.drawable.ic_placeholder_error)
                }
            } else {
                imageView.load(R.drawable.ic_placeholder_error)
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