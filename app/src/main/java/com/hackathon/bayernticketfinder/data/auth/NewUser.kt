package com.hackathon.bayernticketfinder.data.auth

import android.graphics.drawable.Drawable

/**
 * Data class that captures new user information for registration
 */
data class NewUser(
    val userName: String?,
    val userEmail: String,
    val userPassword: String,
    val userImage: Drawable?
)
