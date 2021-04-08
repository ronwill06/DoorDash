package com.example.doordashproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doordashproject.R
import com.example.doordashproject.domain.Restaurant
import com.squareup.picasso.Picasso
import presentation.contracts.RestaurantListPresentationContract

class RestaurantListAdapter(private val restaurantList: List<Restaurant>,
                            private val view: RestaurantListPresentationContract.View):
    RecyclerView.Adapter<RestaurantListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_list_view_holder, parent, false)
        return RestaurantListViewHolder(itemView, view)
    }

    override fun onBindViewHolder(holder: RestaurantListViewHolder, position: Int) {
        holder.setRestaurantData(restaurantList[position])
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }
}

class RestaurantListViewHolder(itemView: View, view: RestaurantListPresentationContract.View): RecyclerView.ViewHolder(itemView) {
    private var restaurant: Restaurant? = null

    init {
        itemView.setOnClickListener {
            restaurant?.let { view.onRowClicked(it) }
        }
    }

    fun setRestaurantData(restaurant: Restaurant) {
        this.restaurant = restaurant
        val imageView = itemView.findViewById<ImageView>(R.id.restaurantImageView)
        Picasso.get().load(restaurant.coverImgUrl).fit().centerCrop().into(imageView)

        val restaurantNameTextView = itemView.findViewById<TextView>(R.id.restaurantNameTextView)
        restaurantNameTextView.text = restaurant.name

        val restaurantCuisineTextView = itemView.findViewById<TextView>(R.id.restaurantDescriptionTextView)
        restaurantCuisineTextView.text = restaurant.description

        //This data was not available in the JSON
       // val restaurantClosingTextView = itemView.findViewById<TextView>(R.id.restaurantClosingTimeTextView)
    }
}