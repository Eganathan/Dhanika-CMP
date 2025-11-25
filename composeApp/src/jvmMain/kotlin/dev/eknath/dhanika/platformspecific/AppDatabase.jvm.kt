package dev.eknath.dhanika.platformspecific

import androidx.room.RoomDatabase
import dev.eknath.dhanika.getDatabaseBuilder
import dev.eknath.dhanika.room.AppDatabase
import dev.eknath.dhanika.room.getRoomDatabase

actual fun getDhanikaDBBuilder(context:Any): RoomDatabase.Builder<AppDatabase> {
    return getDatabaseBuilder()
}