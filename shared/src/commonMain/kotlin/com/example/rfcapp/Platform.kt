package com.example.rfcapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform