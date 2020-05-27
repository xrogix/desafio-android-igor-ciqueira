package com.example.domain.model

data class Wrapper<T>(
    val code: Int,
    val status: String,
    val data: Container<T>
)