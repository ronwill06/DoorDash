package com.example.doordashproject.dagger

import com.example.doordashproject.domain.contracts.RestaurantDomainContract
import dagger.Module
import dagger.Provides
import presentation.RestaurantListPresenter
import presentation.RestaurantListUseCase
import presentation.contracts.RestaurantListPresentationContract
import repositories.RestaurantRepository
import javax.inject.Singleton

@Module
class RestaurantListModule(private val view: RestaurantListPresentationContract.View?) {

    @Provides
    @Singleton
    fun providesRestaurantListUseCase(repository: RestaurantDomainContract.Repository):
            RestaurantListPresentationContract.UseCase {
        return RestaurantListUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesRestaurantListPresenter(useCase: RestaurantListPresentationContract.UseCase):
            RestaurantListPresentationContract.Presenter {
        return RestaurantListPresenter(useCase, view)
    }
}