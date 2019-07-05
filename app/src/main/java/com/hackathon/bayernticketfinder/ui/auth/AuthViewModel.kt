package com.hackathon.bayernticketfinder.ui.auth

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseUser
import com.hackathon.bayernticketfinder.data.Result
import com.hackathon.bayernticketfinder.data.auth.AuthRepository
import com.hackathon.bayernticketfinder.data.auth.User

class AuthViewModel(val repo: AuthRepository) : ViewModel() {

    private val _userLoginCredentials = MutableLiveData<Pair<String, String>>()
    private val _userRegistrationCredentials = MutableLiveData<User>()

    fun login(email: String, password: String) {
        _userLoginCredentials.value = Pair(email, password)
    }

    private val loginResult: LiveData<Result<FirebaseUser>> =
        Transformations.switchMap(_userLoginCredentials) { pair ->
            repo.loginUser(pair.first, pair.second)
        }

    fun register(user: User) {
        _userRegistrationCredentials.value = user
    }

    private val registrationResult: LiveData<Result<FirebaseUser>> =
        Transformations.switchMap(_userRegistrationCredentials) { newUser ->
            repo.registerUser(newUser)
        }

    val result: MediatorLiveData<Result<FirebaseUser>> = MediatorLiveData()

    init {
        result.addSource(loginResult) { result.setValue(it) }
        result.addSource(registrationResult) { result.setValue(it) }
    }

}
