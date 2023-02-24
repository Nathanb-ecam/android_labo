package com.example.labo1

import kotlinx.serialization.Serializable

@Serializable
data class Quote (
    val author : String,
    val content: String
    )
