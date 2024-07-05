package com.example.squarerepos.viewmodel

import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squarerepos.remote.response.ReposResponse
import com.example.squarerepos.remote.response.ReposResponseItem
import com.example.squarerepos.repository.BackendRepository
import com.example.squarerepos.util.Constants.Companion.TAG
import com.example.squarerepos.util.Resource
import kotlinx.coroutines.launch

class ReposOverviewViewModel(
    private val backendRepository: BackendRepository
): ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _repos = mutableStateOf<ReposResponse?>(null)
    val repos: State<ReposResponse?> = _repos

    private val _loadingError = mutableStateOf("")
    val loadingError: State<String> = _loadingError

    init {
        viewModelScope.launch {
            getRepos()
        }
    }

    private fun getRepos() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = backendRepository.getRepos()
            when(result) {
                is Resource.Success -> {
                    result.data?.let {
                        _repos.value = it
                    }
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    result.message?.let {
                        _loadingError.value = it
                    }
                    _isLoading.value = false
                }
                else -> {}
            }
        }
    }
}