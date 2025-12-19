package com.soundlab.soundora.util.ext

fun String.getDeezerCoverUrl(size: Int = 500): String {
    return "https://cdn-images.dzcdn.net/images/cover/$this/${size}x$size-000000-80-0-0.jpg"
}