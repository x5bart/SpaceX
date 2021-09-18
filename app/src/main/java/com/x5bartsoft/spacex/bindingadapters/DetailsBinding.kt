package com.x5bartsoft.spacex.bindingadapters

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.model.response.launchdetail.*
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DetailsBinding {


    companion object {

        @BindingAdapter("getLinkReddit")
        @JvmStatic
        fun getLinkReddit(view: ImageView, date: Reddit) {
            if (date.campaign.isNotEmpty() || date.launch.isNotEmpty() || date.media.isNotEmpty() || date.recovery.isNotEmpty()) {
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

        @BindingAdapter("getLaunchpad")
        @JvmStatic
        fun getLaunchpad(view: TextView, data: Launchpad) {
            val launchpad = "${data.fullName}, ${data.region}"
            view.text = launchpad
        }

        @BindingAdapter("getRocketInfo")
        @JvmStatic
        fun getRocketInfo(view: TextView, data: RocketX) {
            val date = "${data.name}\n First flight: ${data.firstFlight}"
            view.text = date
        }

        @BindingAdapter("getFlightDateDetail")
        @JvmStatic
        fun getFlightDateDetails(view: TextView, date: String) {
            val dateConvert = Instant.parse(date).toLocalDateTime(TimeZone.UTC)
            val date = dateConvert.toString().substringBeforeLast("T")
            view.text = date

        }

        @BindingAdapter("getCapsulesName")
        @JvmStatic
        fun getCapsulesName(view: TextView, data: List<Capsule>?) {

            var capsulesName = ""
            if (data!!.isNotEmpty()) {
                for (i in data) {
                    capsulesName += i.serial + "\n${i.type}"
                }
            }
            if (capsulesName.isEmpty()) capsulesName = "not used in this launch"
            view.text = capsulesName
        }

        @BindingAdapter("getShipsName")
        @JvmStatic
        fun getShipsName(view: TextView, data: List<Ship>?) {
            var shipsName = ""
            if (data!!.isNotEmpty()) {
                for (i in data) {
                    if (i != data[data.size - 1]) shipsName += "${i.type} - ${i.name}\n"
                    else shipsName += "${i.type} - ${i.name}"
                }
            }
            if (shipsName.isEmpty()) shipsName = "none"
            view.text = shipsName
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("getSuccess")
        @JvmStatic
        fun getSuccess(view: TextView, data: Boolean) {
            Log.d("DetailsBinding", "data $data")
            if (data) view.text = "Success"
            else view.text = "Failure"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("getCore")
        @JvmStatic
        fun getCore(view: TextView, data: List<Core>?) {
            if (data!!.isNotEmpty()) {
                val size = data.size
                var core = ""
                for (i in data) {
                    if (i != data[size - 1]) {
                        core += i.core.serial + " reuse count: ${i.core.reuse_count}\n"
                    } else core += i.core.serial + " reuse count: ${i.core.reuse_count}"
                    view.text = core
                }
            } else view.text = "no data"
        }

        @BindingAdapter("loadPatchImageFromUrlDetail")
        @JvmStatic
        fun loadPatchImageFromUrlDetail(imageView: ImageView, imageUrl: String?) {
            Log.d("LaunchesBindingPatch", "imageUrl:$imageUrl")
            if (imageUrl != null) {
                imageView.load(imageUrl) {
                    crossfade(600)
                    transformations(CircleCropTransformation())
                }
            }
        }

        @BindingAdapter("loadMainImageFromUrlDetail")
        @JvmStatic
        fun loadMainImageFromUrlDetail(imageView: ImageView, imageUrl: List<String>?) {
            Log.d("LaunchesBindingMain", "imageUrl:$imageUrl")
            if (imageUrl!!.isNotEmpty()) {
                imageView.load(imageUrl[0]) {
                    crossfade(600)
//                    placeholder(R.drawable.ic_placeholder_error)
                    error(R.drawable.ic_placeholder_error)
                }
            } else {
                imageView.load(R.drawable.ic_placeholder_error)
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