package com.example.squarerepos.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Kept only necessary data to keep the code clean
@Serializable
data class ReposResponseItem(
    @SerialName(value = "description")
    val description: String?,
    @SerialName(value = "full_name")
    val full_name: String?,
    @SerialName(value = "html_url")
    val html_url: String?,
    @SerialName(value = "id")
    val id: Int?,
    @SerialName(value = "language")
    val language: String?,
    @SerialName(value = "name")
    val name: String?,
    @SerialName(value = "owner")
    val owner: Owner?,
    @SerialName(value = "private")
    val `private`: Boolean?,
)