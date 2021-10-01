package com.x5bartsoft.spacex.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.model.response.launchdetail.Rocket

class RocketBinding {

    companion object {
        @BindingAdapter("getRocketFirstFlight")
        @JvmStatic
        fun getRocketFirstFlight(view: TextView, data: String) {
            val text = "First flight: $data"
            view.text = text
        }

        @BindingAdapter("getActive")
        @JvmStatic
        fun getActive(view: ImageView, data: Boolean) {
            if (!data) {
                view.setImageResource(R.drawable.ic_not_active)
                view.setColorFilter(ContextCompat.getColor(view.context, R.color.pink))
            }

        }

        @BindingAdapter("getRocketHeight")
        @JvmStatic
        fun getRocketHeight(view: TextView, data: Rocket) {
            val meters = data.height.meters
            val feet = data.height.feet
            val text = "$meters m / $feet ft"
            view.text = text

        }

        @BindingAdapter("getRocketDiameter")
        @JvmStatic
        fun getRocketDiameter(view: TextView, data: Rocket) {
            val meters = data.diameter.meters
            val feet = data.diameter.feet
            val text = "$meters m / $feet ft"
            view.text = text

        }

        @BindingAdapter("getRocketMass")
        @JvmStatic
        fun getRocketMass(view: TextView, data: Rocket) {
            val kg = data.mass.kg
            val lbs = data.mass.lb
            val text = "$kg kg / $lbs lbs"
            view.text = text

        }

        @BindingAdapter("getRocketLaunchSuccessRate")
        @JvmStatic
        fun getRocketLaunchSuccessRate(view: TextView, data: Rocket) {
            val rate = data.successRatePct
            val text = "$rate%"
            view.text = text

        }

        @BindingAdapter("getRocketEngines")
        @JvmStatic
        fun getRocketEngines(view: TextView, data: Rocket) {
            val engines = data.firstStage.engines
            val type = data.engines.type
            val version = data.engines.version
            val text = "$engines, $type $version"
            view.text = text

        }

        @BindingAdapter("getRocketEnginesSecond")
        @JvmStatic
        fun getRocketEnginesSecond(view: TextView, data: Rocket) {
            val engines = data.secondStage.engines
            val type = data.engines.type
            val version = data.engines.version
            val text = "$engines, $type $version"
            view.text = text

        }

        @BindingAdapter("getRocketTrustSeaLevel")
        @JvmStatic
        fun getRocketTrustSeaLevel(view: TextView, data: Rocket) {
            val kN = data.firstStage.thrustSeaLevel.kN
            val lbf = data.firstStage.thrustSeaLevel.lbf
            val text = "$kN kN / $lbf lbf"
            view.text = text

        }

        @BindingAdapter("getRocketTrustVacuum")
        @JvmStatic
        fun getRocketTrustVacuum(view: TextView, data: Rocket) {
            val kN = data.firstStage.thrustVacuum.kN
            val lbf = data.firstStage.thrustVacuum.lbf
            val text = "$kN kN / $lbf lbf"
            view.text = text

        }

        @BindingAdapter("getRocketTrust")
        @JvmStatic
        fun getRocketTrust(view: TextView, data: Rocket) {
            val kN = data.secondStage.thrust.kN
            val lbf = data.secondStage.thrust.lbf
            val text = "$kN kN / $lbf lbf"
            view.text = text

        }

        @BindingAdapter("getRocketTons")
        @JvmStatic
        fun getRocketTons(view: TextView, data: Double) {
            val text = "$data tons"
            view.text = text

        }

        @BindingAdapter("getRocketBurnTime")
        @JvmStatic
        fun getRocketBurnTime(view: TextView, data: Double) {
            val text = "$data sec"
            view.text = text

        }




    }
}