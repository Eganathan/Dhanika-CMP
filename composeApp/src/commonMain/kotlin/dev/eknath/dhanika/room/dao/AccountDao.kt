package dev.eknath.dhanika.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.eknath.dhanika.room.models.accounts.LocalAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM ACCOUNT")
    fun getAllAccounts(): Flow<List<LocalAccount>>

    @Query("SELECT * FROM ACCOUNT WHERE accountId = :accountId")
    suspend fun getAccountById(accountId: Long): LocalAccount?

    @Query("SELECT * FROM ACCOUNT WHERE accountType = :accountType")
    fun getAccountsByType(accountType: Int): Flow<List<LocalAccount>>

    @Upsert
    suspend fun upsertAccount(account: LocalAccount)

    @Delete
    suspend fun deleteAccount(account: LocalAccount)

    @Query("UPDATE ACCOUNT SET balance = :newBalance WHERE accountId = :accountId")
    suspend fun updateBalance(accountId: Long, newBalance: Double)
}
