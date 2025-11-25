package dev.eknath.dhanika.repository

import dev.eknath.dhanika.room.dao.UserInfoDao
import dev.eknath.dhanika.room.models.LocalUserInfo

interface UserRepository {
    val localSource: UserInfoDao
    suspend fun getUserInfo(): LocalUserInfo?
    suspend fun updateUserInfo(user: LocalUserInfo)
}