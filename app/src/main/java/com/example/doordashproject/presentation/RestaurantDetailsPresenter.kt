package presentation

import io.reactivex.rxjava3.core.Scheduler
import presentation.contracts.RestaurantDetailsPresentationContract

class RestaurantDetailsPresenter(
    private val useCase: RestaurantDetailsPresentationContract.UseCase,
    private val view: RestaurantDetailsPresentationContract.View?,
    private val backgroundScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler
) : RestaurantDetailsPresentationContract.Presenter {

    override fun fetchRestaurantDetails(id: String) {
        useCase.getRestaurantDetails(id)
            .subscribeOn(backgroundScheduler)
            .observeOn(mainThreadScheduler)
            .subscribe({ restaurantDetails ->
                view?.onRestaurantFetched(restaurantDetails)
            }, {
                view?.onError(it)
            })
    }
}