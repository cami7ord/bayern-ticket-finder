package com.hackathon.bayernticketfinder.ui.create

import android.app.DatePickerDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.hackathon.bayernticketfinder.R
import com.hackathon.bayernticketfinder.ui.auth.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel

import kotlinx.android.synthetic.main.create_trip_fragment.*
import org.koin.android.ext.android.inject
import java.util.*


class CreateTripFragment : Fragment() {

    companion object {
        fun newInstance() = CreateTripFragment()
    }

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.create_trip_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()

        viewModel.result.observe(this@CreateTripFragment, Observer {
            Log.d("pawel", "${it.toString()}")
        })
    }

    fun setupLayout() {

        departure_time_edit.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                departure_time_edit.setText("$dayOfMonth/$monthOfYear/$year")
            }, year, month, day).show()

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}
