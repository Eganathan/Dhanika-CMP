package dev.eknath.dhanika.util

import co.touchlab.kermit.Logger

// This helpers me ensure i can change the logger dependency later if need be
object Log {}

fun Log.d(tag: String = "CMP", message: String) {
    Logger.d(tag ?: "", message = { message })
}