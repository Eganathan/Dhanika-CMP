package dev.eknath.dhanika

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.eknath.dhanika.room.AppDatabase

internal fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("dhanika_.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}