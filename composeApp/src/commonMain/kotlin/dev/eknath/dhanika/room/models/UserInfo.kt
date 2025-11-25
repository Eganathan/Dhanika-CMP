package dev.eknath.dhanika.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUserInfo(
    @PrimaryKey(autoGenerate = false) val id: Long = 0L,
    val name: String
)
