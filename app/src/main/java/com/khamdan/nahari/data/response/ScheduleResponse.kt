package com.khamdan.nahari.data.response

import com.khamdan.nahari.domain.Schedule
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    val id: Int? = null,
    val image: Image? = null,
    val rating: Rating? = null,
    @SerialName("_embedded")
    val embedded: Embedded? = null,
    val name: String? = "",
)

@Serializable
data class Image(
    val medium: String? = ""
)

@Serializable
data class Rating(
    val average: Double? = 0.0
)


@Serializable
data class Show(
    val status: String? = ""
)


@Serializable
data class Embedded(
    val show: Show? = null
)

fun ScheduleResponse.toScheduleModel(): Schedule {
    return Schedule(
        name = name.orEmpty(),
        image = image?.medium.orEmpty(),
        rating = rating?.average ?: 0.0,
        status = embedded?.show?.status.orEmpty()
    )
}