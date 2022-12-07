package com.khamdan.nahari.data.response

import kotlinx.serialization.Serializable

@Serializable
data class Users(
    val per_page: Int = 0,
    val total: Int = 0,
    val data: List<User>?,
    val page: Int = 0,
    val total_pages: Int = 0,
    val support: Support
)

@Serializable
data class User(
    val last_name: String = "",
    val id: Int = 0,
    val avatar: String = "",
    val first_name: String = "",
    val email: String = ""
)

@Serializable
data class Support(
    val text: String = "",
    val url: String = ""
)
