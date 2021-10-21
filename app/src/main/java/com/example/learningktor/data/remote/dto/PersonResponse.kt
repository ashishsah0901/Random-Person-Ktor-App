package com.example.learningktor.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
    val id: Int,
    val name: String,
    val imageUrl: String
)