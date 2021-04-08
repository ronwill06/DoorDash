package repositories

import com.example.doordashproject.domain.contracts.RestaurantDomainContract
import com.example.doordashproject.domain.RestaurantDetail
import com.example.doordashproject.domain.RestaurantStore
import io.reactivex.rxjava3.core.Single

class RestaurantRepository(private val remoteDataSource: RestaurantDomainContract.RemoteDataStore):
   RestaurantDomainContract.Repository {

    override fun getRestaurants(): Single<RestaurantStore> {
        return remoteDataSource.getRestaurantList("37.422740", "-122.139956")
    }

    override fun getRestaurantDetails(id: String): Single<RestaurantDetail> {
        return remoteDataSource.getRestaurantDetails(id)
    }
}