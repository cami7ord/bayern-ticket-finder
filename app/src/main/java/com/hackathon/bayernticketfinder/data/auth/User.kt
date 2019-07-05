package com.hackathon.bayernticketfinder.data.auth

/**
 * Data class that captures new user information for registration
 */
data class User(
    val userName: String?,
    val userEmail: String,
    val userPassword: String,
    val userImage: String
)
