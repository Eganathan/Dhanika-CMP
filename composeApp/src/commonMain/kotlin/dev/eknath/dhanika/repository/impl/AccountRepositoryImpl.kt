package dev.eknath.dhanika.repository.impl

import dev.eknath.dhanika.repository.AccountRepository
import dev.eknath.dhanika.room.dao.AccountDao
import dev.eknath.dhanika.room.models.accounts.LocalAccount
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class AccountRepositoryImpl(override val localSource: AccountDao) : AccountRepository {

    override fun getAllAccounts(): Flow<List<LocalAccount>> {
        return localSource.getAllAccounts()
    }

    override suspend fun getAccountById(accountId: Long): LocalAccount? {
        return localSource.getAccountById(accountId)
    }

    override fun getAccountsByType(accountType: Int): Flow<List<LocalAccount>> {
        return localSource.getAccountsByType(accountType)
    }

    override suspend fun saveAccount(account: LocalAccount) {
        localSource.upsertAccount(account)
    }

    override suspend fun deleteAccount(account: LocalAccount) {
        localSource.deleteAccount(account)
    }

    override suspend fun updateBalance(accountId: Long, newBalance: Double) {
        localSource.updateBalance(accountId, newBalance)
    }
}
