package com.example.data.services

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageProvider(
    private val context: Context
) {
    fun renderImage(url: String, target: ImageView, type: Int = 0) {
        Glide.with(context)
            .load(if( type == 0) "$url/standard_medium.jpg" else "$url/portrait_medium.jpg")
            .into(target)
    }
}