package com.example.squarerepos.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName(value = "avatar_url")
    val avatar_url: String?,
    @SerialName(value = "id")
    val id: Int?,
    @SerialName(value = "login")
    val login: String?,
    @SerialName(value = "type")
    val type: String?,
)