package dev.eknath.dhanika.room.models.accounts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("ACCOUNT")
data class LocalAccount(
    @PrimaryKey val accountId: Long,
    val accountName: String,
    val openingBalance: Double = 0.0,
    val balance: Double,
    val color: Long,
    val accountType: Int,// CURRENT, SAVINGS, RECURRING, SERVICE(GEMINI,YT,SPOTIFY...),INVESTMENTS
    val recurringAmount: Double = 0.0,//RECURRING, SERVICE, SAVINGS,
    val recurringType: Int // Daily,Weekly,Monthly,BiMonthly,Quarterly,HalfYearly,Annual
)
