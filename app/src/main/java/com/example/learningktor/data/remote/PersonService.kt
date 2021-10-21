package com.example.learningktor.data.remote

import com.example.learningktor.data.remote.dto.PersonRequest
import com.example.learningktor.data.remote.dto.PersonResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PersonService {

    suspend fun getRandomPerson() : PersonResponse

    suspend fun postRandomPerson(personRequest: PersonRequest) : PersonResponse

    companion object {
        private var INSTANCE: PersonService? = null
        fun create(): PersonService {
            return INSTANCE ?: PersonServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            ).also {
                INSTANCE = it
            }
        }
    }

}