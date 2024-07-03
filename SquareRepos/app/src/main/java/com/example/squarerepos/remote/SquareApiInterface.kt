package com.example.squarerepos.remote

import com.example.squarerepos.remote.response.ReposResponse
import retrofit2.http.GET

interface SquareApiInterface {
    @GET("repos")
    suspend fun getRepos() : ReposResponse
}