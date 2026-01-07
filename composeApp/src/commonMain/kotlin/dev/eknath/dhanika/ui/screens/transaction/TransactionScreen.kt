package dev.eknath.dhanika.ui.screens.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.eknath.dhanika.room.models.transactions.LocalExpense
import dev.eknath.dhanika.room.models.transactions.LocalIncome
import dev.eknath.dhanika.ui.viewmodel.TransactionViewModel
import kotlinx.datetime.*

@OptIn(kotlin.time.ExperimentalTime::class)
@Composable
fun TransactionScreen(viewModel: TransactionViewModel) {
    val expenses by viewModel.expenses.collectAsState()
    val incomes by viewModel.incomes.collectAsState()
    val isLoading by viewModel.isLoading
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) }
    var transactionType by remember { mutableStateOf(TransactionType.EXPENSE) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                transactionType = if (selectedTab == 0) TransactionType.EXPENSE else TransactionType.INCOME
                showAddDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Transaction")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Transactions",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Expenses") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Income") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                when (selectedTab) {
                    0 -> ExpenseList(expenses, viewModel)
                    1 -> IncomeList(incomes, viewModel)
                }
            }
        }

        if (showAddDialog) {
            AddTransactionDialog(
                type = transactionType,
                onDismiss = { showAddDialog = false },
                onSave = { transaction ->
                    when (transaction) {
                        is LocalExpense -> viewModel.saveExpense(transaction, onSuccess = {
                            showAddDialog = false
                        })
                        is LocalIncome -> viewModel.saveIncome(transaction, onSuccess = {
                            showAddDialog = false
                        })
                    }
                }
            )
        }
    }
}

@Composable
fun ExpenseList(expenses: List<LocalExpense>, viewModel: TransactionViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(expenses) { expense ->
            TransactionItem(
                title = expense.title,
                amount = expense.amount,
                date = expense.date,
                isExpense = true,
                onDelete = { viewModel.deleteExpense(expense) }
            )
        }
    }
}

@Composable
fun IncomeList(incomes: List<LocalIncome>, viewModel: TransactionViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(incomes) { income ->
            TransactionItem(
                title = income.title,
                amount = income.amount,
                date = income.date,
                isExpense = false,
                onDelete = { viewModel.deleteIncome(income) }
            )
        }
    }
}

@Composable
fun TransactionItem(
    title: String,
    amount: Double,
    date: Long,
    isExpense: Boolean,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatDate(date),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${if (isExpense) "-" else "+"}$$amount",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isExpense) Color(0xFFF44336) else Color(0xFF4CAF50)
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Transaction",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AddTransactionDialog(
    type: TransactionType,
    onDismiss: () -> Unit,
    onSave: (Any) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf(1L) }
    var accountId by remember { mutableStateOf<Long?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add ${if (type == TransactionType.EXPENSE) "Expense" else "Income"}") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val currentTime = Clock.System.now().toEpochMilliseconds()
                    when (type) {
                        TransactionType.EXPENSE -> {
                            onSave(
                                LocalExpense(
                                    id = currentTime,
                                    title = title,
                                    amount = amount.toDoubleOrNull() ?: 0.0,
                                    date = currentTime,
                                    createdDate = currentTime,
                                    updatedDate = currentTime,
                                    associatedCategoryId = categoryId,
                                    associatedAccountId = accountId
                                )
                            )
                        }
                        TransactionType.INCOME -> {
                            onSave(
                                LocalIncome(
                                    id = currentTime,
                                    title = title,
                                    amount = amount.toDoubleOrNull() ?: 0.0,
                                    date = currentTime,
                                    createdDate = currentTime,
                                    updatedDate = currentTime,
                                    associatedCategoryId = categoryId,
                                    associatedAccountId = accountId
                                )
                            )
                        }
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

enum class TransactionType {
    EXPENSE, INCOME
}

fun formatDate(timestamp: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    val localDateTime = instant.toLocalDateTime(TimeZone.UTC)
    return "${localDateTime.dayOfMonth}/${localDateTime.monthNumber}/${localDateTime.year}"
}
