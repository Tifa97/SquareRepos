package com.example.squarerepos.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    @SerialName(value = "avatar_url")
    val avatar_url: String?,
    @SerialName(value = "events_url")
    val events_url: String?,
    @SerialName(value = "followers_url")
    val followers_url: String?,
    @SerialName(value = "following_url")
    val following_url: String?,
    @SerialName(value = "gists_url")
    val gists_url: String?,
    @SerialName(value = "gravatar_id")
    val gravatar_id: String?,
    @SerialName(value = "html_url")
    val html_url: String?,
    @SerialName(value = "id")
    val id: Int?,
    @SerialName(value = "login")
    val login: String?,
    @SerialName(value = "node_id")
    val node_id: String?,
    @SerialName(value = "organizations_url")
    val organizations_url: String?,
    @SerialName(value = "received_events_url")
    val received_events_url: String?,
    @SerialName(value = "repos_url")
    val repos_url: String?,
    @SerialName(value = "site_admin")
    val site_admin: Boolean?,
    @SerialName(value = "starred_url")
    val starred_url: String?,
    @SerialName(value = "subscriptions_url")
    val subscriptions_url: String?,
    @SerialName(value = "type")
    val type: String?,
    @SerialName(value = "url")
    val url: String?
)