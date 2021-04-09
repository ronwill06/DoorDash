package com.example.doordashproject

import com.example.doordashproject.domain.Restaurant
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.*
import presentation.RestaurantListPresenter
import presentation.RestaurantListUseCase
import presentation.contracts.RestaurantListPresentationContract
import java.lang.Exception
import java.net.SocketTimeoutException

class RestaurantListPresenterTest {

    lateinit var presenter: RestaurantListPresenter

    @Mock
    lateinit var useCase: RestaurantListUseCase

    @Mock
    lateinit var view: RestaurantListPresentationContract.View

    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        presenter = RestaurantListPresenter(useCase, view, testScheduler, testScheduler)
    }

    @Test
    fun getRestaurantListIsSuccessful() {
        val restaurant = Restaurant("1", "Restaurant", "American", "https://img.png")
        val restaurant2 = Restaurant("2", "Restaurant", "American", "https://img.png")
        val restaurants = listOf(restaurant, restaurant2)
        Mockito.`when`(useCase.getRestaurants()).then { Single.just(restaurants)}

        presenter.fetchRestaurants()
        testScheduler.triggerActions()

        Mockito.verify(view).onFetchedRestaurants(restaurants)
    }

    @Test
    fun getRestaurantListIsSuccessfulButListIsEmpty() {
        Mockito.`when`(useCase.getRestaurants()).then { Single.just(emptyList<Restaurant>()) }

        presenter.fetchRestaurants()
        testScheduler.triggerActions()

        Mockito.verify(view).onRestaurantsListEmpty()
    }

    @Test
    fun getRestaurantListErrors() {
        val exception = SocketTimeoutException()
        Mockito.`when`(useCase.getRestaurants()).then { Single.error<Exception>(exception) }

        presenter.fetchRestaurants()
        testScheduler.triggerActions()

        Mockito.verify(view).onError(exception)
    }
}