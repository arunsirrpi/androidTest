package au.net.va.vatest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import au.net.va.vatest.R
import au.net.va.vatest.databinding.ItemTripRowBinding
import au.net.va.vatest.model.Trip

class MainAdapter(private val clickListener: ((Trip) -> Unit)?=null) :
    ListAdapter<Trip, MainAdapter.ViewHolder>(TripDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_trip_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var binding: ItemTripRowBinding
        fun bind(trip: Trip, clickListener: ((Trip) -> Unit)?) {
            binding = ItemTripRowBinding.bind(itemView)
            binding.pnr.text = trip.pnr
            binding.journey.text = trip.departurePort + " to " + trip.arrivalPort
            itemView.setOnClickListener {
                clickListener?.run {
                    this(trip)
                }
            }
        }
    }
}
