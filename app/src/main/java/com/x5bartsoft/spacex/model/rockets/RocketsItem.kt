package com.x5bartsoft.spacex.model.rockets


import com.google.gson.annotations.SerializedName

data class RocketsItem(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

)