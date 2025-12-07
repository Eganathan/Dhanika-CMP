package dev.eknath.dhanika.room.models.transactions

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import dev.eknath.dhanika.room.models.LocalCategory

@Entity(
    tableName = "EXPENSE",
    foreignKeys = [ForeignKey(
        entity = LocalCategory::class,
        parentColumns = ["associatedCategoryId"],
        childColumns = ["categoryId"]
    )]
)
data class LocalExpense(
    @PrimaryKey override val id: Long,
    override val title: String,
    override val amount: Double,
    override val date: Long,
    override val createdDate: Long,
    override val updatedDate: Long,
    override val associatedCategoryId: Long,
    override val associatedAccountId: Long? = null
) : BaseTransactionEntity()