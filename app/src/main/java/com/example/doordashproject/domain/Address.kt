package com.example.doordashproject.domain

import com.google.gson.annotations.SerializedName

data class Address(@SerializedName("printable_address")
                    val fullAddress: String)
