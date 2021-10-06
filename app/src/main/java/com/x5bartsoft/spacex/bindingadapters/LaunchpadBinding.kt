package com.x5bartsoft.spacex.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.model.response.launchdetail.Landpad
import com.x5bartsoft.spacex.model.response.launchdetail.Launchpad

class LaunchpadBinding {

    companion object {
        @BindingAdapter("getLaunchpadLocality")
        @JvmStatic
        fun getLaunchpadLocality(view: TextView, data: Launchpad?) {
            if (data != null) {
                val locality = data.locality
                val region = data.region
                val text = "$locality, $region"
                view.text = text
            }
        }

        @BindingAdapter("getLaunchpadRockets")
        @JvmStatic
        fun getLaunchpadRockets(view: TextView, data: Launchpad) {
            var count = 0
            var text = ""
            for (i in data.rockets) {
                count += 1
                text += i.name
                if (count != data.rockets.size) text += ", "
            }
            view.text = text
        }
    }
}