package com.khamdan.nahari.data.response

import kotlinx.serialization.Serializable


@Serializable //the response from server just like this, no token, different from the documentation :(
data class Login(
    val per_page: Int = 0,
    val total: Int = 0,
    val data: List<DataItem>?,
    val page: Int = 0,
    val total_pages: Int = 0
)

@Serializable
data class DataItem(
    val color: String = "",
    val year: Int = 0,
    val name: String = "",
    val id: Int = 0,
    val pantone_value: String = ""
)
