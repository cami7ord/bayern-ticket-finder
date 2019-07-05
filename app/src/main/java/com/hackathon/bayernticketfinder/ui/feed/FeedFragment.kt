package com.hackathon.bayernticketfinder.ui.feed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.hackathon.bayernticketfinder.R
import com.hackathon.bayernticketfinder.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.feed_fragment.*

class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

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
        // TODO: Use the ViewModel

        logout_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, AuthActivity::class.java))
        }
    }

}
