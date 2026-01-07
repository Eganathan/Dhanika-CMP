package dev.eknath.dhanika.ui.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import dev.eknath.dhanika.room.models.accounts.LocalAccount
import dev.eknath.dhanika.ui.viewmodel.AccountViewModel
import kotlinx.datetime.Clock

@Composable
fun AccountScreen(viewModel: AccountViewModel) {
    val accounts by viewModel.accounts.collectAsState()
    val isLoading by viewModel.isLoading
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Account")
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
                text = "Accounts",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(accounts) { account ->
                        AccountItem(
                            account = account,
                            onDelete = { viewModel.deleteAccount(account) }
                        )
                    }
                }
            }
        }

        if (showAddDialog) {
            AddAccountDialog(
                onDismiss = { showAddDialog = false },
                onSave = { account ->
                    viewModel.saveAccount(account, onSuccess = {
                        showAddDialog = false
                    })
                }
            )
        }
    }
}

@Composable
fun AccountItem(account: LocalAccount, onDelete: () -> Unit) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(Color(account.color), shape = RoundedCornerShape(6.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = account.accountName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Balance: ${account.balance}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (account.balance >= 0) Color(0xFF4CAF50) else Color(0xFFF44336)
                )
                Text(
                    text = "Type: ${getAccountTypeName(account.accountType)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Account",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AddAccountDialog(onDismiss: () -> Unit, onSave: (LocalAccount) -> Unit) {
    var accountName by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("0.0") }
    var openingBalance by remember { mutableStateOf("0.0") }
    var accountType by remember { mutableStateOf(0) }
    var color by remember { mutableStateOf(0xFF2196F3L) }
    var recurringAmount by remember { mutableStateOf("0.0") }
    var recurringType by remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Account") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = accountName,
                    onValueChange = { accountName = it },
                    label = { Text("Account Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = openingBalance,
                    onValueChange = { openingBalance = it },
                    label = { Text("Opening Balance") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = balance,
                    onValueChange = { balance = it },
                    label = { Text("Current Balance") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Account Type:", style = MaterialTheme.typography.labelMedium)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = accountType == 0,
                        onClick = { accountType = 0 },
                        label = { Text("Current") }
                    )
                    FilterChip(
                        selected = accountType == 1,
                        onClick = { accountType = 1 },
                        label = { Text("Savings") }
                    )
                    FilterChip(
                        selected = accountType == 2,
                        onClick = { accountType = 2 },
                        label = { Text("Recurring") }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSave(
                        LocalAccount(
                            accountId = Clock.System.now().toEpochMilliseconds(),
                            accountName = accountName,
                            openingBalance = openingBalance.toDoubleOrNull() ?: 0.0,
                            balance = balance.toDoubleOrNull() ?: 0.0,
                            color = color,
                            accountType = accountType,
                            recurringAmount = recurringAmount.toDoubleOrNull() ?: 0.0,
                            recurringType = recurringType
                        )
                    )
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

fun getAccountTypeName(type: Int): String {
    return when (type) {
        0 -> "Current"
        1 -> "Savings"
        2 -> "Recurring"
        3 -> "Service"
        4 -> "Investments"
        else -> "Unknown"
    }
}
