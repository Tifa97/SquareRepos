package com.example.squarerepos.remote

import com.example.squarerepos.remote.response.ReposResponse
import com.example.squarerepos.remote.response.ReposResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface SquareApiInterface {
    @GET("orgs/square/repos")
    suspend fun getRepos() : ReposResponse

    @GET("repos/square/{name}")
    suspend fun getRepoByName(
        @Path("name") name: String
    ): ReposResponseItem
}