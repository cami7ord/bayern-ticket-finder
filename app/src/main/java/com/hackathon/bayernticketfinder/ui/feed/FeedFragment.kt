package com.hackathon.bayernticketfinder.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.bayernticketfinder.R
import com.hackathon.bayernticketfinder.data.auth.User
import com.hackathon.bayernticketfinder.data.feed.Trip
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import java.util.*

class FeedFragment : Fragment() {

    private val user = User("Julia", "julia@gmail.com", "pass123", "https://miro.medium.com/fit/c/256/256/1*qsoys-tVFpMW-fWWSANGfA.jpeg")
    private val user2 = User("Jhon", "jhon@gmail.com", "pass123", "https://upload.wikimedia.org/wikipedia/en/a/a1/NafSadh_Profile.jpg")

    private val trips = listOf(
        Trip(true, "Somewhere", Date(), 2, user, "Starting point", "5. July 2019", 5),
        Trip(true, "Somewhere", Date(), 2, user2, "Starting point", "5. July 2019", 5)
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MultiViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = MultiViewAdapter()

        viewAdapter.registerItemBinders(TripBinder())

        recyclerView = view?.findViewById<RecyclerView>(R.id.feed_recycler)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }!!

        val listSection = ListSection<Trip>()
        listSection.addAll(trips)

        // Add Section to the adapter
        viewAdapter.addSection(listSection)

    }

}
