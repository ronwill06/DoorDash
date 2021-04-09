package com.example.doordashproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doordashproject.R
import com.example.doordashproject.dagger.DaggerDoorDashComponent
import com.example.doordashproject.dagger.NetworkModule
import com.example.doordashproject.dagger.RestaurantDetailsModule
import com.example.doordashproject.dagger.RestaurantListModule
import com.example.doordashproject.domain.Restaurant
import presentation.contracts.RestaurantListPresentationContract
import javax.inject.Inject

class RestaurantListFragment : Fragment(), RestaurantListPresentationContract.View {

    @Inject
    lateinit var presenter: RestaurantListPresentationContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    companion object {
        @JvmStatic
        fun newInstance() =
            RestaurantListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDoorDashComponent
            .builder()
            .networkModule(NetworkModule())
            .restaurantListModule(RestaurantListModule(this))
            .restaurantDetailsModule(RestaurantDetailsModule(null))
            .build()
            .inject(this)
        presenter.fetchRestaurants()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resturant_list, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        recyclerView = view.findViewById(R.id.restaurantListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        errorTextView = view.findViewById(R.id.errorMessage)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchRestaurants()
    }

    override fun onFetchedRestaurants(restaurantList: List<Restaurant>) {
        progressBar.visibility = View.GONE
        recyclerView.adapter = RestaurantListAdapter(restaurantList, this)
    }

    override fun onRestaurantsListEmpty() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = getString(R.string.no_restaurants_available)
    }

    override fun onError(error: Throwable) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = error.message.orEmpty()
    }

    override fun onRowClicked(restaurant: Restaurant) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, RestaurantDetailsFragment.newInstance(restaurant.id))
            ?.addToBackStack(null)
            ?.commit()
    }
}