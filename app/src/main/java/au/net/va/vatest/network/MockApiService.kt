package au.net.va.vatest.network

import au.net.va.vatest.domain.OnTripsLoadListener
import au.net.va.vatest.model.Trip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.Executors

private val jobExecutor = Executors.newFixedThreadPool(5)

private var listener: OnTripsLoadListener? = null

private fun getContents(): List<Trip> =
    Gson().fromJson(
        Resource().getResponse(),
        object : TypeToken<List<Trip?>?>() {}.type
    )

fun loadTrips(listener1: OnTripsLoadListener) {
    listener = listener1
    jobExecutor.execute {
        val trips: List<Trip> = getContents()
        Thread.sleep(3000)

        listener?.onTripLoaded(trips)

    }

}