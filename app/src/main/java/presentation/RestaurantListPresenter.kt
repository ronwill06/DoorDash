package presentation

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import presentation.contracts.RestaurantListPresentationContract
import java.lang.Exception

class RestaurantListPresenter(
    private val useCase: RestaurantListPresentationContract.UseCase,
    private val view: RestaurantListPresentationContract.View?) : RestaurantListPresentationContract.Presenter {

    override fun fetchRestaurants() {
        useCase.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ restaurants ->
                if (restaurants.isNotEmpty()) {
                    view?.onFetchedRestaurants(restaurants)
                } else {
                    view?.onError(Exception("Sorry! We could not find any restaurants in your area"))
                }
            }, {
                view?.onError(it)
            })
    }
}
