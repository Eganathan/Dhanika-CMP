package dev.eknath.dhanika.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.eknath.dhanika.room.models.LocalUserInfo

@Dao
interface UserInfoDao {
    @Upsert
    suspend fun upsetUserInfo(item: LocalUserInfo)

    @Query("SELECT * FROM LocalUserInfo WHERE id is 0 LIMIT 1")
    suspend fun getUserInfo(): LocalUserInfo?
}