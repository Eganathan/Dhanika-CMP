package dev.eknath.dhanika

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform