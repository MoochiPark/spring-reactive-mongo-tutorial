package me.daewon.springreactivemongotutorial

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "sensing_data")
data class SensingData(
    @Id
    val id: String? = null,
    val data: String,
    val sensingTime: LocalDateTime,
)
