package au.net.va.vatest.ui

import androidx.recyclerview.widget.DiffUtil
import au.net.va.vatest.model.Trip

class TripDiffCallback : DiffUtil.ItemCallback<Trip>() {
    override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
        return oldItem.pnr == newItem.pnr
    }

    override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
        return oldItem == newItem
    }
}