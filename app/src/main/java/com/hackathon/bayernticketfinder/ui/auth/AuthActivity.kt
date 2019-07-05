package com.hackathon.bayernticketfinder.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.hackathon.bayernticketfinder.MainActivity
import com.hackathon.bayernticketfinder.R
import com.hackathon.bayernticketfinder.data.Result
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.android.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    // Lazy Inject ViewModel
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login_button.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel.login(
                email = email.text.toString(),
                password = password.text.toString()
            )
        }

        viewModel.result.observe(this@AuthActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult is Result.Error) {
                Toast.makeText(this, "Login Error", Toast.LENGTH_LONG).show()
            }
            if (loginResult is Result.Success) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                startActivity(intent)
                finishAfterTransition()
            }

        })

    }
}
