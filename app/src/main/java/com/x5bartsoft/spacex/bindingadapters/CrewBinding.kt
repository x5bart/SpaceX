package com.x5bartsoft.spacex.bindingadapters

import android.content.Intent
import android.net.Uri
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

    @BindingAdapter("getCrewLink")
    @JvmStatic
    fun getCrewLink(view: ImageView, date: String?) {
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