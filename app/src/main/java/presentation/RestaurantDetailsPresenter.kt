package presentation

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import presentation.contracts.RestaurantDetailsPresentationContract

class RestaurantDetailsPresenter(
    private val useCase: RestaurantDetailsPresentationContract.UseCase,
    private val view: RestaurantDetailsPresentationContract.View?
) : RestaurantDetailsPresentationContract.Presenter {
    private val TAG = RestaurantDetailsPresentationContract::class.java.simpleName

    override fun fetchRestaurantDetails(id: String) {
        useCase.getRestaurantDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ restaurantDetails ->
                view?.onRestaurantFetched(restaurantDetails)
            }, {
                view?.onError(it)
            })
    }
}