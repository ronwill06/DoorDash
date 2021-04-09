package presentation

import com.example.doordashproject.domain.RestaurantDetail
import com.example.doordashproject.domain.contracts.RestaurantDomainContract
import io.reactivex.rxjava3.core.Single
import presentation.contracts.RestaurantDetailsPresentationContract

class RestaurantDetailsUseCase(private val repository: RestaurantDomainContract.Repository):
    RestaurantDetailsPresentationContract.UseCase {

    override fun getRestaurantDetails(id: String): Single<RestaurantDetail> {
       return repository.getRestaurantDetails(id)
    }
}