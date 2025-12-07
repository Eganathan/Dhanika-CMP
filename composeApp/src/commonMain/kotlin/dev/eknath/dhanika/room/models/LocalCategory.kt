package dev.eknath.dhanika.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//Pre-populate some on init

@Entity("CATEGORY")
data class LocalCategory(
    @PrimaryKey val categoryId: Long,
    val name: String,
    val accountType: Int, // 1 -> Income, 2 -> Expense, 3 -> (Reimbursements/Refunds/Others)
    val color: Long,
    val associatedAccountId: Long? = null
)