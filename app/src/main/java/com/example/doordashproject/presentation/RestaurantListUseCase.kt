package presentation

import com.example.doordashproject.domain.Restaurant
import com.example.doordashproject.domain.contracts.RestaurantDomainContract
import io.reactivex.rxjava3.core.Single
import presentation.contracts.RestaurantListPresentationContract

class RestaurantListUseCase(private val repository: RestaurantDomainContract.Repository):
    RestaurantListPresentationContract.UseCase {
    override fun getRestaurants(): Single<List<Restaurant>> {
        return repository.getRestaurants()
            .map { store -> store.restaurants }
    }
}