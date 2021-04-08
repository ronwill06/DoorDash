package com.example.doordashproject.domain

import com.google.gson.annotations.SerializedName

data class RestaurantDetail(val id: String,
                            val name: String,
                            val description: String,
                            @SerializedName("cover_img_url")
                            val coverImgUrl: String,
                            @SerializedName("phone_number")
                            val phoneNumber: String,
                            @SerializedName("offers_delivery")
                            val offersDelivery: Boolean,
                            @SerializedName("address")
                            val address: Address)

