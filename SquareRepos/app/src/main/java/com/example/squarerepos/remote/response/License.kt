package com.example.squarerepos.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class License(
    @SerialName(value = "key")
    val key: String?,
    @SerialName(value = "name")
    val name: String?,
    @SerialName(value = "node_id")
    val node_id: String?,
    @SerialName(value = "spdx_id")
    val spdx_id: String?,
    @SerialName(value = "url")
    val url: String?
)