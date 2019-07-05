package com.hackathon.bayernticketfinder.data.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hackathon.bayernticketfinder.data.Result

interface AuthRepository {
    fun loginUser(email: String, password: String): LiveData<Result<FirebaseUser>>
    fun registerUser(newUser: NewUser): LiveData<Result<FirebaseUser>>
}

class AuthRepositoryImpl : AuthRepository {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun loginUser(email: String, password: String): LiveData<Result<FirebaseUser>> {

        val loginResult = MutableLiveData<Result<FirebaseUser>>()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.d("AuthRepository", "signInWithEmail:success")
                    loginResult.postValue(Result.Success(auth.currentUser as FirebaseUser))
                } else {
                    Log.w("AuthRepository", "signInWithEmail:failure", task.exception)
                    loginResult.postValue(Result.Error(task.exception!!))
                }
            }
        return loginResult
    }

    override fun registerUser(newUser: NewUser): LiveData<Result<FirebaseUser>> {

        val registrationResult = MutableLiveData<Result<FirebaseUser>>()

        auth.createUserWithEmailAndPassword(newUser.userEmail, newUser.userPassword)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.d("AuthRepository", "signInWithEmail:success")
                    registrationResult.postValue(Result.Success(auth.currentUser as FirebaseUser))
                } else {
                    Log.w("AuthRepository", "signInWithEmail:failure", task.exception)
                    registrationResult.postValue(Result.Error(task.exception!!))
                }
            }
        return registrationResult
    }

}
