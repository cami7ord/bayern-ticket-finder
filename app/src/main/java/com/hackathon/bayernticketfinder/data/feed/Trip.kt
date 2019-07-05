package com.hackathon.bayernticketfinder.data.feed

import com.hackathon.bayernticketfinder.data.auth.User
import java.util.*

/**
 * Data class that captures a Trip model information
 */
data class Trip(
    val active: Boolean,
    val destination: String, // Change to a destination
    val finish_time: Date,
    val number_of_people: Int,
    val owner: User,
    val starting_point: String?,
    val starting_time: String?,
    val tripSize: Int = 5
)