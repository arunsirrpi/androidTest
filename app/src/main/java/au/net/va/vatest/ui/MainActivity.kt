package au.net.va.vatest.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.net.va.vatest.R
import au.net.va.vatest.databinding.ActivityMainBinding
import au.net.va.vatest.domain.OnTripsLoadListener
import au.net.va.vatest.model.Trip
import au.net.va.vatest.network.loadTrips
import au.net.va.vatest.updateCount

class MainActivity : AppCompatActivity(), OnTripsLoadListener {

    private lateinit var mainAdapter: MainAdapter
    private lateinit var binding: ActivityMainBinding
    private var list: RecyclerView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        list = findViewById(R.id.list)
        mainAdapter = MainAdapter()
        list?.layoutManager = LinearLayoutManager(this)
        list?.adapter = mainAdapter
        findViewById<TextView>(R.id.tripCount).text = getString(R.string.trip_count, 0)

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        loadTrips(this)
    }

    private fun onItemClickListener(trip: Trip) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(trip.contactus)
        }.also {
            startActivity(it)
        }
    }

    override fun onTripLoaded(trips: List<Trip>) {
        updateCount(binding.tripCount, getString(R.string.trip_count, trips.size))
        mainAdapter.submitList(trips)
    }

}
