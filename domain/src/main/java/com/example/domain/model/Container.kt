package com.example.domain.model

data class Container<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: MutableList<T>
)