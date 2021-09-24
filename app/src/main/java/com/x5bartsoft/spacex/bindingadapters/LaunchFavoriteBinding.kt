package com.x5bartsoft.spacex.bindingadapters

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.adapters.FavoriteLaunchesAdapter
import com.x5bartsoft.spacex.data.database.etities.FavoriteEntity

class LaunchFavoriteBinding {

    companion object {

        @BindingAdapter("setVisibility", "setData", requireAll = false)
        @JvmStatic
        fun setVisibility(
            view: View,
            favoriteEntity: List<FavoriteEntity>?,
            mAdapter: FavoriteLaunchesAdapter?,
        ) {
            when (view) {
                is RecyclerView -> {
                    val dataCheck = favoriteEntity.isNullOrEmpty()
                    view.isInvisible = dataCheck
                    if (!dataCheck) {
                        favoriteEntity?.let { mAdapter?.setData(it) }
                    }
                }
                else -> view.isVisible = favoriteEntity.isNullOrEmpty()
            }
        }
    }
}