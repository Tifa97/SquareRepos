package com.example.squarerepos.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReposResponseItem(
    @SerialName(value = "clone_url")
    val clone_url: String?,
    @SerialName(value = "created_at")
    val created_at: String?,
    @SerialName(value = "default_branch")
    val default_branch: String?,
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
    @SerialName(value = "license")
    val license: License?,
    @SerialName(value = "name")
    val name: String?,
    @SerialName(value = "open_issues")
    val open_issues: Int?,
    @SerialName(value = "owner")
    val owner: Owner?,
    @SerialName(value = "private")
    val `private`: Boolean?,
    @SerialName(value = "size")
    val size: Int?,
    @SerialName(value = "ssh_url")
    val ssh_url: String?,
    @SerialName(value = "topics")
    val topics: List<String>?,
    @SerialName(value = "updated_at")
    val updated_at: String?,
    @SerialName(value = "visibility")
    val visibility: String?,
)