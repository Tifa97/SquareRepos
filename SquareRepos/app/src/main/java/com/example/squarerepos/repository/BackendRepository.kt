package com.example.squarerepos.repository

import android.content.Context
import android.util.Log
import com.example.squarerepos.R
import com.example.squarerepos.remote.SquareApiClient
import com.example.squarerepos.remote.response.ReposResponse
import com.example.squarerepos.remote.response.ReposResponseItem
import com.example.squarerepos.util.Constants.Companion.TAG
import com.example.squarerepos.util.Resource

class BackendRepository(
    private val squareApi: SquareApiClient,
    private val context: Context
) {
    suspend fun getRepos(): Resource<ReposResponse> {
        val result = try {
            squareApi.retrofitService.getRepos()
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            return Resource.Error(context.getString(R.string.error_while_fetching_repos))
        }
        return Resource.Success(result)
    }

    suspend fun getRepoByName(name: String): Resource<ReposResponseItem> {
        val result = try {
            squareApi.retrofitService.getRepoByName(name)
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            return Resource.Error(context.getString(R.string.error_fetching_repo_with_name, name))
        }
        return Resource.Success(result)
    }
}