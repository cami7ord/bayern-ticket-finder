package com.hackathon.bayernticketfinder.data.trips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.hackathon.bayernticketfinder.data.Result

interface DestinationsRepository {
    fun getDestinations(): LiveData<Result<List<Destination>>>
}

class DestinationsRepositoryImpl : DestinationsRepository {

    val db = FirebaseFirestore.getInstance().collection("destinations")

    override fun getDestinations(): LiveData<Result<List<Destination>>> {

        val destinationsResult = MutableLiveData<Result<List<Destination>>>()

        db.get().addOnSuccessListener { result ->
            val list = mutableListOf<Destination>()
            result.documents.forEach {
                list.add(Destination(it.data?.get("name") as String, it.data?.get("description")  as String))
            }

            destinationsResult.postValue(Result.Success(list))
        }.addOnFailureListener {
            destinationsResult.postValue(Result.Error(it))

        }

        return destinationsResult
    }
}