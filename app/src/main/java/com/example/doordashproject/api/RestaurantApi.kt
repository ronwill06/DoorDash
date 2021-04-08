package com.example.doordashproject.api

import com.example.doordashproject.domain.RestaurantDetail
import com.example.doordashproject.domain.RestaurantStore
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RestaurantApi {

    @GET("v1/store_feed/")
    fun getRestaurantList(@Query("lat") lat: String, @Query("lng") lng: String,
                          @Query("offset") offset: Int = 0, @Query("limit") limit: Int = 50):
            Single<RestaurantStore>

    @GET("/v2/restaurant/{id}")
    fun getRestaurantDetail(@Path("id") id: String): Single<RestaurantDetail>

}