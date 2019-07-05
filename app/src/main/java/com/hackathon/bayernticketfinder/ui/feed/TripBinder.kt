package com.hackathon.bayernticketfinder.ui.feed

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hackathon.bayernticketfinder.R
import com.hackathon.bayernticketfinder.data.feed.Trip
import com.hackathon.bayernticketfinder.utils.PicassoCircleTransformation
import com.squareup.picasso.Picasso
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class TripBinder : ItemBinder<Trip, TripBinder.ViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        val view: View = inflate(parent, R.layout.item_trip)
        return ViewHolder(view)
    }

    override fun canBindData(item: Any): Boolean {
        return item is Trip
    }

    override fun bindViewHolder(holder: ViewHolder, item: Trip) {
        holder.ownerNameView.text = item.owner.userName
        holder.tripStartingDateView.text = item.starting_time

        Picasso.get().load(item.owner.userImage).transform(PicassoCircleTransformation()).into(holder.ownerImageView)
        Picasso.get().load("https://www.swedishnomad.com/wp-content/images/2018/07/neuschwanstein-castle.jpg").into(holder.tripImageView)
    }

    class ViewHolder(itemView: View) : ItemViewHolder<Trip>(itemView) {

        var ownerImageView: ImageView = itemView.findViewById(R.id.trip_owner_image_view)
        var ownerNameView: TextView = itemView.findViewById(R.id.trip_owner_name_view)
        var tripStartingDateView: TextView = itemView.findViewById(R.id.trip_starting_date_view)
        var tripImageView: ImageView = itemView.findViewById(R.id.trip__destination_image)
        //TODO the rest
    }

}