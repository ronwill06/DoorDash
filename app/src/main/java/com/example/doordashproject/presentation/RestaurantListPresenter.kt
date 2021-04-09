package presentation

import io.reactivex.rxjava3.core.Scheduler
import presentation.contracts.RestaurantListPresentationContract
import java.lang.Exception

class RestaurantListPresenter(
    private val useCase: RestaurantListPresentationContract.UseCase,
    private val view: RestaurantListPresentationContract.View?,
    private val backgroundScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler
) : RestaurantListPresentationContract.Presenter {

    override fun fetchRestaurants() {
        useCase.getRestaurants()
            .subscribeOn(backgroundScheduler)
            .observeOn(mainThreadScheduler)
            .subscribe({ restaurants ->
                if (restaurants.isNotEmpty()) {
                    view?.onFetchedRestaurants(restaurants)
                } else {
                    view?.onRestaurantsListEmpty()
                }
            }, {
                view?.onError(it)
            })
    }
}
