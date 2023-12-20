package au.net.va.vatest.domain

import au.net.va.vatest.model.Trip

interface OnTripsLoadListener {
    fun onTripLoaded(trips: List<Trip>)
}