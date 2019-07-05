package com.hackathon.bayernticketfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.hackathon.bayernticketfinder.ui.auth.AuthActivity
import com.hackathon.bayernticketfinder.ui.feed.FeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (FirebaseAuth.getInstance().currentUser != null) {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FeedFragment.newInstance())
                    .commitNow()
            }
        } else {
            val intent = Intent(this@MainActivity, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}
