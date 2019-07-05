package com.hackathon.bayernticketfinder.ui.auth

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.hackathon.bayernticketfinder.data.Result
import com.hackathon.bayernticketfinder.data.auth.AuthRepository
import com.hackathon.bayernticketfinder.data.auth.NewUser
import com.hackathon.bayernticketfinder.data.trips.Destination
import com.hackathon.bayernticketfinder.data.trips.DestinationsRepository

class AuthViewModel(val repo: AuthRepository, val destinationsRepo: DestinationsRepository) : ViewModel() {

    private val _userLoginCredentials = MutableLiveData<Pair<String, String>>()
    private val _userRegistrationCredentials = MutableLiveData<NewUser>()

    private val destinationsResult: LiveData<Result<List<Destination>>> = destinationsRepo.getDestinations()

    fun login(email: String, password: String) {
        _userLoginCredentials.value = Pair(email, password)
    }

    private val loginResult: LiveData<Result<FirebaseUser>> =
        Transformations.switchMap(_userLoginCredentials) { pair ->
            repo.loginUser(pair.first, pair.second)
        }

    fun register(newUser: NewUser) {
        _userRegistrationCredentials.value = newUser
    }

    private val registrationResult: LiveData<Result<FirebaseUser>> =
        Transformations.switchMap(_userRegistrationCredentials) { newUser ->
            repo.registerUser(newUser)
        }

    val result: MediatorLiveData<Result<FirebaseUser>> = MediatorLiveData()
    private val mediatorDestinationsResult: MediatorLiveData<Result<List<Destination>>> = MediatorLiveData()

    init {
        result.addSource(loginResult) { result.setValue(it) }
        result.addSource(registrationResult) { result.setValue(it) }
        mediatorDestinationsResult.addSource(destinationsResult) { mediatorDestinationsResult.value = it }
    }

}
