package dev.eknath.dhanika.repository.impl

import dev.eknath.dhanika.repository.UserRepository
import dev.eknath.dhanika.room.dao.UserInfoDao
import dev.eknath.dhanika.room.models.LocalUserInfo
import org.koin.core.annotation.Single


@Single
class UserRepositoryImpl(override val localSource: UserInfoDao) : UserRepository {

    override suspend fun getUserInfo(): LocalUserInfo? {
        return localSource.getUserInfo()
    }

    override suspend fun updateUserInfo(user: LocalUserInfo) {
        return localSource.upsetUserInfo(user)
    }

}