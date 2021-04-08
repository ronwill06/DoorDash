package com.example.doordashproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.doordashproject.R
import com.example.doordashproject.dagger.DaggerDoorDashComponent
import com.example.doordashproject.dagger.NetworkModule
import com.example.doordashproject.dagger.RestaurantDetailsModule
import com.example.doordashproject.dagger.RestaurantListModule
import com.example.doordashproject.domain.Restaurant
import com.example.doordashproject.domain.RestaurantDetail
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import presentation.contracts.RestaurantDetailsPresentationContract
import javax.inject.Inject

class RestaurantDetailsFragment : Fragment(), RestaurantDetailsPresentationContract.View {

    @Inject
    lateinit var presenter: RestaurantDetailsPresentationContract.Presenter

    private var id: String? = null

    private lateinit var restaurantImageView: ImageView
    private lateinit var restaurantNameTextView: TextView
    private lateinit var restaurantDescriptionTextView: TextView
    private lateinit var restaurantPhoneNumberTextView: TextView
    private lateinit var restaurantAddressTextView: TextView
    private lateinit var restaurantDelivers: TextView
    private lateinit var restaurantDeliversTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    companion object {

        @JvmStatic
        fun newInstance(id: String) =
            RestaurantDetailsFragment().apply {
                this.id = id
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDoorDashComponent
            .builder()
            .networkModule(NetworkModule())
            .restaurantListModule(RestaurantListModule(null))
            .restaurantDetailsModule(RestaurantDetailsModule(this))
            .build()
            .inject(this)
        id?.let { presenter.fetchRestaurantDetails(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        restaurantImageView = view.findViewById(R.id.restaurantImageView)
        restaurantNameTextView = view.findViewById(R.id.restaurantNameTextView)
        restaurantDescriptionTextView = view.findViewById(R.id.restaurantDescriptionTextView)
        restaurantPhoneNumberTextView = view.findViewById(R.id.restaurantPhoneNumberTextView)
        restaurantAddressTextView = view.findViewById(R.id.restaurantAddressTextView)
        restaurantDelivers = view.findViewById(R.id.restaurantDelivery)
        restaurantDelivers.visibility = View.INVISIBLE
        restaurantDeliversTextView = view.findViewById(R.id.restaurantDeliversTextView)
        errorTextView = view.findViewById(R.id.errorMessage)
        return view
    }

    override fun onRestaurantFetched(restaurantDetails: RestaurantDetail) {
        progressBar.visibility = View.GONE
        restaurantDelivers.visibility = View.VISIBLE
        Picasso.get().load(restaurantDetails.coverImgUrl).fit().centerCrop().into(restaurantImageView)
        restaurantNameTextView.text = restaurantDetails.name
        restaurantDescriptionTextView.text = restaurantDetails.description
        restaurantPhoneNumberTextView.text = restaurantDetails.phoneNumber
        restaurantAddressTextView.text = restaurantDetails.address.fullAddress
        restaurantDeliversTextView.text = if (restaurantDetails.offersDelivery) "Yes" else "No"
    }

    override fun onError(error: Throwable) {
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
        restaurantImageView.visibility = View.GONE
        restaurantNameTextView.visibility = View.GONE
        restaurantDescriptionTextView.visibility = View.GONE
        errorTextView.text = error.message.orEmpty()
    }
}