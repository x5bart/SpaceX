package com.x5bartsoft.spacex.bindingadapters

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.data.Lib
import com.x5bartsoft.spacex.util.formatTo
import com.x5bartsoft.spacex.util.toDate
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDate

class LaunchesBinding {

    companion object {
        @BindingAdapter("loadPatchImageFromUrl")
        @JvmStatic
        fun loadPatchImageFromUrl(imageView: ImageView, imageUrl: String?) {
            Log.d("LaunchesBinding", "imageUrl:$imageUrl")
            if (imageUrl != null) {
                imageView.load(imageUrl) {
                    crossfade(600)
                    placeholder(R.drawable.ic_placeholder_error)
                    error(R.drawable.ic_placeholder_error)
                    transformations(CircleCropTransformation())
                }
            } else {
                imageView.load(R.drawable.ic_placeholder_error)
            }
        }

        @BindingAdapter("loadMainImageFromUrl")
        @JvmStatic
        fun loadMainImageFromUrl(imageView: ImageView, imageUrl: List<String>?) {
            Log.d("LaunchesBinding", "imageUrl:$imageUrl")
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

        @SuppressLint("SetTextI18n")
        @BindingAdapter("getRocketName")
        @JvmStatic
        fun getRocketName(view: TextView, id: String?) {
            if (id != null) view.text = Lib.rocketsName[id]
            else view.text = "No data"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("getLaunchpadName")
        @JvmStatic
        fun getLaunchpadsName(view: TextView, id: String?) {
            if (id != null) view.text = Lib.launchpadsName[id]
            else view.text = "No data"
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