package com.example.doordashproject.domain.contracts

import com.example.doordashproject.domain.Restaurant
import com.example.doordashproject.domain.RestaurantDetail
import com.example.doordashproject.domain.RestaurantStore
import io.reactivex.rxjava3.core.Single

interface RestaurantDomainContract {
    interface LocalDataStore {}
    interface RemoteDataStore {
        fun getRestaurantList(lat: String, long: String): Single<RestaurantStore>
        fun getRestaurantDetails(id: String): Single<RestaurantDetail>
    }
    interface Repository {
        fun getRestaurants(): Single<RestaurantStore>
        fun getRestaurantDetails(id: String): Single<RestaurantDetail>
    }
}