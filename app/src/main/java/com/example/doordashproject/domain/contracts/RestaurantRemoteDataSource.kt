package com.example.doordashproject.domain.contracts

import com.example.doordashproject.api.RestaurantApi
import com.example.doordashproject.domain.Restaurant
import com.example.doordashproject.domain.RestaurantDetail
import com.example.doordashproject.domain.RestaurantStore
import io.reactivex.rxjava3.core.Single

class RestaurantRemoteDataSource(private val api: RestaurantApi) : RestaurantDomainContract.RemoteDataStore {
    override fun getRestaurantList(lat: String, long: String): Single<RestaurantStore> {
        return api.getRestaurantList(lat, long)
    }

    override fun getRestaurantDetails(id: String): Single<RestaurantDetail> {
        return api.getRestaurantDetail(id)
    }
}