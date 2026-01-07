package dev.eknath.dhanika.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.eknath.dhanika.room.dao.AccountDao
import dev.eknath.dhanika.room.dao.CategoryDao
import dev.eknath.dhanika.room.dao.ExpenseDao
import dev.eknath.dhanika.room.dao.IncomeDao
import dev.eknath.dhanika.room.dao.UserInfoDao
import dev.eknath.dhanika.room.models.LocalCategory
import dev.eknath.dhanika.room.models.LocalUserInfo
import dev.eknath.dhanika.room.models.accounts.LocalAccount
import dev.eknath.dhanika.room.models.transactions.LocalExpense
import dev.eknath.dhanika.room.models.transactions.LocalIncome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        LocalUserInfo::class,
        LocalAccount::class,
        LocalCategory::class,
        LocalExpense::class,
        LocalIncome::class
    ],
    version = 2,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun incomeDao(): IncomeDao
}