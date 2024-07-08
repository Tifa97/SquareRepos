package com.example.squarerepos.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squarerepos.remote.response.ReposResponseItem
import com.example.squarerepos.repository.BackendRepository
import com.example.squarerepos.util.Constants.Companion.TAG
import com.example.squarerepos.util.Resource
import kotlinx.coroutines.launch

class RepoDetailsViewModel(
    private val backendRepository: BackendRepository
): ViewModel() {
    private val _repo = mutableStateOf<ReposResponseItem?>(null)
    val repo: State<ReposResponseItem?> = _repo

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _loadError = mutableStateOf("")
    val loadError: State<String> = _loadError

    fun getRepoByName(repoName: String) {
        Log.i(TAG, "Called getRepoByName()")
        viewModelScope.launch {
            _isLoading.value = true
            val result = backendRepository.getRepoByName(repoName)
            when(result) {
                is Resource.Success -> {
                    result.data?.let {
                        _repo.value = it
                    }
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    result.message?.let {
                        _loadError.value = it
                        _isLoading.value = false
                    }
                }
                else -> {}
            }
        }
    }
}