package com.example.domain.model

data class Container(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: MutableList<Characters>
)