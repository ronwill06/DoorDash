package presentation.contracts

import com.example.doordashproject.domain.Restaurant
import io.reactivex.rxjava3.core.Single

interface RestaurantListPresentationContract {
    interface UseCase {
        fun getRestaurants(): Single<List<Restaurant>>
    }
    interface Presenter {
        fun fetchRestaurants()
    }
    interface View {
        fun onFetchedRestaurants(restaurantList: List<Restaurant>)
        fun onError(error: Throwable)
        fun onRowClicked(restaurant: Restaurant)
    }
}