package com.example.domain.model

import java.util.Date

data class Characters(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Thumbnail
)