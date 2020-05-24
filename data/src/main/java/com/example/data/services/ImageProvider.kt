package com.example.data.services

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageProvider(
    private val context: Context
) {
    fun renderImage(url: String, target: ImageView) {
        //TODO ajustar variável de imagens
        Glide.with(context)
            .load("$url/standard_medium.jpg")
            .into(target)
    }
}