package com.example.doordashproject.domain

import com.google.gson.annotations.SerializedName

data class Restaurant(val id: String,
                      val name: String,
                      val description: String,
                      @SerializedName("cover_img_url")
                      val coverImgUrl: String)