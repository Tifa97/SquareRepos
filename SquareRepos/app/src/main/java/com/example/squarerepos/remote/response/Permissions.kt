package com.example.squarerepos.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Permissions(
    @SerialName(value = "admin")
    val admin: Boolean?,
    @SerialName(value = "maintain")
    val maintain: Boolean?,
    @SerialName(value = "pull")
    val pull: Boolean?,
    @SerialName(value = "push")
    val push: Boolean?,
    @SerialName(value = "triage")
    val triage: Boolean?
)