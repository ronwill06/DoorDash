package com.example.doordashproject.dagger

import com.example.doordashproject.domain.contracts.RestaurantDomainContract
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import presentation.RestaurantDetailsPresenter
import presentation.RestaurantDetailsUseCase
import presentation.contracts.RestaurantDetailsPresentationContract
import repositories.RestaurantRepository
import javax.inject.Singleton

@Module
class RestaurantDetailsModule(private val view: RestaurantDetailsPresentationContract.View?) {

    @Provides
    @Singleton
    fun providesRestaurantDetailsUseCase(repository: RestaurantDomainContract.Repository):
            RestaurantDetailsPresentationContract.UseCase {
        return RestaurantDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesRestaurantDetailsPresenter(useCase: RestaurantDetailsPresentationContract.UseCase):
            RestaurantDetailsPresentationContract.Presenter {
        return RestaurantDetailsPresenter(useCase, view, Schedulers.io(), AndroidSchedulers.mainThread())
    }

}