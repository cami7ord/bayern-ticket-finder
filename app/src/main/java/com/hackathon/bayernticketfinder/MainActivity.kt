package com.hackathon.bayernticketfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackathon.bayernticketfinder.ui.feed.FeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FeedFragment.newInstance())
                .commitNow()
        }
    }

}
