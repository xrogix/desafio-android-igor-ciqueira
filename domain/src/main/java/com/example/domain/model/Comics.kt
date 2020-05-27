package com.example.domain.model

data class Comics(
    val id: Int,
    val title: String,
    val variantDescription: String,
    val description: String,
    val prices: ArrayList<Prices>,
    val thumbnail: Thumbnail,
    val images: List<Thumbnail>
)