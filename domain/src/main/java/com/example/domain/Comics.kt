package com.example.domain

data class Comics(
    val id: Int,
    val title: String,
    val variantDescription: String,
    val description: String,
    val prices: String,
    val thumbnail: Thumbnail,
    val images: List<Thumbnail>
)