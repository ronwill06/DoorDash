package com.example.doordashproject.domain

import com.google.gson.annotations.SerializedName

data class RestaurantStore(
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("is_first_time_user")
    val isFirstTimeUser: Boolean,
    @SerializedName("stores")
    val restaurants: List<Restaurant>
) {
}