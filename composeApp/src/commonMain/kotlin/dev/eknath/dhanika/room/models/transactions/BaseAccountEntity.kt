package dev.eknath.dhanika.room.models.transactions

abstract class BaseTransactionEntity {
    abstract val id: Long
    abstract val title: String
    abstract val amount: Double
    abstract val date: Long
    abstract val createdDate: Long
    abstract val updatedDate: Long

    abstract val associatedCategoryId: Long
    abstract val associatedAccountId: Long?
}