package com.example.doordashproject

import com.example.doordashproject.domain.Address
import com.example.doordashproject.domain.Restaurant
import com.example.doordashproject.domain.RestaurantDetail
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import presentation.RestaurantDetailsPresenter
import presentation.RestaurantDetailsUseCase
import presentation.contracts.RestaurantDetailsPresentationContract
import java.lang.Exception
import java.net.SocketTimeoutException

class RestaurantDetailsPresenterTest {

    lateinit var presenter: RestaurantDetailsPresenter

    @Mock
    lateinit var useCase: RestaurantDetailsUseCase

    @Mock
    lateinit var view: RestaurantDetailsPresentationContract.View

    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        presenter = RestaurantDetailsPresenter(useCase, view, testScheduler, testScheduler)
    }

    @Test
    fun getRestaurantDetailIsSuccessful() {
        val restaurant = Restaurant("1", "Restaurant", "American", "https://img.png")
        val address = Address("155 Mullholand Drive, Los Angeles, CA")
        val restaurantDetail = RestaurantDetail("1", "Restaurant", "American", "https://img.png", "+16255991234", true, address)
        Mockito.`when`(useCase.getRestaurantDetails(restaurant.id)).then { Single.just(restaurantDetail)}

        presenter.fetchRestaurantDetails(restaurant.id)
        testScheduler.triggerActions()

        Mockito.verify(view).onRestaurantFetched(restaurantDetail)
    }

    @Test
    fun getRestaurantDetailsError() {
        val exception = SocketTimeoutException()
        Mockito.`when`(useCase.getRestaurantDetails("1")).then { Single.error<Exception>(exception) }

        presenter.fetchRestaurantDetails("1")
        testScheduler.triggerActions()

        Mockito.verify(view).onError(exception)
    }
}