package com.x5bartsoft.spacex.bindingadapters

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.x5bartsoft.spacex.R

class CrewBinding {
companion object{
    @BindingAdapter("getCrewActive")
    @JvmStatic
    fun getCrewActive(view: ImageView, data: String) {
        if (data=="active") {
            view.setImageResource(R.drawable.ic_active)
            view.setColorFilter(ContextCompat.getColor(view.context, R.color.lightGreen))
        }

    }
}

}