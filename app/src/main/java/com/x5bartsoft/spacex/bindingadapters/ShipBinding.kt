package com.x5bartsoft.spacex.bindingadapters

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.model.response.launchdetail.Launchpad
import com.x5bartsoft.spacex.model.response.launchdetail.Rocket
import com.x5bartsoft.spacex.model.response.launchdetail.Ship

class ShipBinding {

    companion object{


        @BindingAdapter("getShipRole")
        @JvmStatic
        fun getShipRole(view: TextView, data: Ship) {
            var count = 0
            var text = ""
            for (i in data.roles) {
                count += 1
                text += i
                if (count != data.roles.size) text += ", "
            }
            view.text = text
        }

        @BindingAdapter("getShipMass")
        @JvmStatic
        fun getShipMass(view: TextView, data: Ship) {
            val kg = data.massKg
            val lbs = data.massLbs
            val text = "$kg kg / $lbs lbs"
            view.text = text

        }

        @BindingAdapter("getShipActive")
        @JvmStatic
        fun getShipActive(view: ImageView, data: Boolean) {
            if (!data) {
                view.setImageResource(R.drawable.ic_not_active)
                view.setColorFilter(ContextCompat.getColor(view.context, R.color.pink))
            }

        }

        @BindingAdapter("setShipVisibleInt")
        @JvmStatic
        fun setShipVisibleInt(view: TextView, data: Int?) {
            if (data == 0) view.visibility = View.GONE
        }

        @BindingAdapter("getShipLink")
        @JvmStatic
        fun getShipLink(view: ImageView, date: String?) {
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