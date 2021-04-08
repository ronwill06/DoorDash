package com.example.doordashproject.dagger

import com.example.doordashproject.ui.RestaurantDetailsFragment
import com.example.doordashproject.ui.RestaurantListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RestaurantListModule::class, RestaurantDetailsModule::class])
interface DoorDashComponent {
    fun inject(restaurantListFragment: RestaurantListFragment)
    fun inject(restaurantDetailFragment: RestaurantDetailsFragment)
}