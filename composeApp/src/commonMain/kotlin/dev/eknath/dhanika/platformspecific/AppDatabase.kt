package dev.eknath.dhanika.platformspecific

import androidx.room.RoomDatabase
import dev.eknath.dhanika.room.AppDatabase
import dev.eknath.dhanika.room.getRoomDatabase

expect fun getDhanikaDBBuilder(context: Any): RoomDatabase.Builder<AppDatabase>

internal fun getDhanikaDatabase(context: Any): AppDatabase {
    return getRoomDatabase(getDhanikaDBBuilder(context))
}