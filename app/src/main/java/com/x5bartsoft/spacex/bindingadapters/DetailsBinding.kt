package com.x5bartsoft.spacex.bindingadapters

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.model.response.launchdetail.*

class DetailsBinding {


    companion object {

        @BindingAdapter("getRole")
        @JvmStatic
        fun getRole(view: TextView,data: Ship){
            var text = ""
            var count = 0
            for (i in data.roles){
                count+=1
                text += i
                if (count!= data.roles.size) text +=", "
            }
            view.text = text
        }

        @BindingAdapter("getSuccessLaunch")
        @JvmStatic
        fun getSuccessLaunch(view: TextView,data: Launchpad){
            val count = data.launchSuccesses
            val text = "$count success launches"
            view.text = text
        }


        @BindingAdapter("getSuccessRate")
        @JvmStatic
        fun getSuccessRate(view: TextView, data: Rocket){
            val percent = data.successRatePct
            val text = "Success rate $percent%"
            view.text = text
        }


        @BindingAdapter("getCost")
        @JvmStatic
        fun getCost(view: TextView, data: Rocket){
            val cost = data.costPerLaunch
            val text = "Cost per launch:$cost$"
            view.text = text
        }

        @BindingAdapter("getLendingLegs")
        @JvmStatic
        fun getLendingLegs(view: TextView, data: LandingLegs) {
            val text = if (data.number!=0) {
                val count = data.number
                val material = data.material
                "$count $material landing legs"
            } else "not used on this rocket"
            view.text = text
        }

        @BindingAdapter("getSecondFuel")
        @JvmStatic
        fun getSecondFuel(view: TextView, data: Rocket) {
            val tons = data.secondStage.fuelAmountTons
            val propellant1 = data.engines.propellant1
            val propellant2 = data.engines.propellant2
            val text = "Fuel amount - $tons tons \n$propellant1 + $propellant2"
            view.text = text
        }

        @BindingAdapter("getSecondThrust")
        @JvmStatic
        fun getSecondThrust(view: TextView, data: SecondStage) {
            val thrust = data.thrust.kN
            val text = "Thrust  - $thrust kN"
            view.text = text
        }

        @BindingAdapter("getSecondEngines")
        @JvmStatic
        fun getSecondEngines(view: TextView, data: Rocket) {
            val engineCount = data.secondStage.engines
            val engineText = if (engineCount > 1) "engines" else "engine"
            val engineType = data.type
            val text = "$engineCount $engineText $engineType"
            view.text = text
        }


        @BindingAdapter("getFirstFuel")
        @JvmStatic
        fun getFirstFuel(view: TextView, data: Rocket) {
            val tons = data.firstStage.fuelAmountTons
            val propellant1 = data.engines.propellant1
            val propellant2 = data.engines.propellant2
            val text = "Fuel amount - $tons tons \n$propellant1 + $propellant2"
            view.text = text
        }

        @BindingAdapter("getFirstThrust")
        @JvmStatic
        fun getFirstThrust(view: TextView, data: FirstStage) {
            val seaLevel = data.thrustSeaLevel.kN
            val vacuum = data.thrustVacuum.kN
            val text = "Thrust sea level - $seaLevel kN \nThrust vacuum - $vacuum kN"
            view.text = text
        }

        @BindingAdapter("getFirstEngines")
        @JvmStatic
        fun getFirstEngines(view: TextView, data: Rocket) {
            val engineCount = data.firstStage.engines
            val engineText = if (engineCount > 1) "engines" else "engine"
            val engineType = data.type
            val text = "$engineCount $engineText $engineType"
            view.text = text
        }

        @BindingAdapter("getLinkReddit")
        @JvmStatic
        fun getLinkReddit(view: ImageView, date: Reddit) {
            if (date.campaign != null || date.launch != null || date.media != null || date.recovery != null) {
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
            val fullName = data.fullName
            val region = data.region
            val text = "$fullName, $region"
            view.text = text
        }

        @BindingAdapter("getRocketInfo")
        @JvmStatic
        fun getRocketInfo(view: TextView, data: Rocket) {
            val name = data.name
            val firstFlight = data.firstFlight
            val text = "$name\n First flight: $firstFlight"
            view.text = text
        }

        @BindingAdapter("getCapsulesName")
        @JvmStatic
        fun getCapsulesName(view: TextView, data: List<Capsule>?) {
            var text = ""
            if (data!!.isNotEmpty()) {
                var count = 0
                for (i in data) {
                    count += 1
                    val serial = i.serial
                    val type = i.type
                    text += "$serial \n$type"
                    if (count != data.size) text += "\n"
                }
            }
            if (text.isEmpty()) text = "not used in this launch"
            view.text = text
        }

        @BindingAdapter("getShipsName")
        @JvmStatic
        fun getShipsName(view: TextView, data: List<Ship>?) {
            var text = ""
            if (data!!.isNotEmpty()) {
                var count = 0
                for (i in data) {
                    count += 1
                    val type = i.type
                    val name = i.name
                    text += "$type - $name"
                    if (count != data.size) text += "\n"
                }
            }
            if (text.isEmpty()) text = "none"
            view.text = text
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("getCore")
        @JvmStatic
        fun getCore(view: TextView, data: List<Core>?) {
            if (data!!.isNotEmpty()) {
                var text = ""
                var count = 0
                for (i in data) {
                    count += 1
                    val serial = i.serial
                    val reuseCount = i.reuseCount
                    text += "$serial repeated use $reuseCount times"
                    if (count != data.size) text += "\n"
                }
                view.text = text
            } else view.text = "no data"
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