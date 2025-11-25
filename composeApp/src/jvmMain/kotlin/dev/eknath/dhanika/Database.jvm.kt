package dev.eknath.dhanika

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.eknath.dhanika.room.AppDatabase
import java.io.File

internal fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "dhanika_.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}