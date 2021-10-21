package com.example.learningktor.data.remote

import com.example.learningktor.data.remote.dto.PersonRequest
import com.example.learningktor.data.remote.dto.PersonResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class PersonServiceImpl(
    private val client: HttpClient
): PersonService {

    override suspend fun getRandomPerson(): PersonResponse {
        return try {
            client.get {
                url(HttpRoutes.PERSON)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            PersonResponse(0,"","")
        }
    }

    override suspend fun postRandomPerson(personRequest: PersonRequest): PersonResponse {
        return try {
            client.post<PersonResponse> {
                url(HttpRoutes.PERSON)
                contentType(ContentType.Application.Json)
                body = personRequest
            }
        } catch (e: Exception) {
            e.printStackTrace()
            PersonResponse(0,"","")
        }
    }

}