package com.example.learningktor.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonRequest(
    val name: String,
    val imageUrl: String
)