package dev.eknath.dhanika.platformspecific

import android.content.Context
import androidx.room.RoomDatabase
import dev.eknath.dhanika.getDatabaseBuilder
import dev.eknath.dhanika.room.AppDatabase

actual fun getDhanikaDBBuilder(context: Any): RoomDatabase.Builder<AppDatabase> {
    return getDatabaseBuilder(context as Context)
}