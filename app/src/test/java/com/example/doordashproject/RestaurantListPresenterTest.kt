package com.example.doordashproject

import com.example.doordashproject.domain.Restaurant
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import presentation.RestaurantListPresenter
import presentation.RestaurantListUseCase
import presentation.contracts.RestaurantListPresentationContract

class RestaurantListPresenterTest {

    @Mock
    lateinit var presenter: RestaurantListPresenter

    @Mock
    lateinit var useCase: RestaurantListUseCase

    @Mock
    lateinit var view: RestaurantListPresentationContract.View

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getRestaurantListIsSuccessful() {
        val restaurant = Restaurant("1", "Restaurant", "American", "https://img.png")
        val restaurants = listOf(restaurant)
        presenter.fetchRestaurants()
        Mockito.`when`(useCase.getRestaurants()).then { Single.just(restaurants)}
        Mockito.verify(view.onFetchedRestaurants(restaurants))
    }
}