package presentation.contracts

import com.example.doordashproject.domain.Restaurant
import com.example.doordashproject.domain.RestaurantDetail
import io.reactivex.rxjava3.core.Single
import java.lang.Exception

class RestaurantDetailsPresentationContract {
    interface UseCase {
        fun getRestaurantDetails(id: String): Single<RestaurantDetail>
    }
    interface Presenter {
        fun fetchRestaurantDetails(id: String)
    }
    interface View {
        fun onRestaurantFetched(restaurantDetails: RestaurantDetail)
        fun onError(error: Throwable)
    }
}