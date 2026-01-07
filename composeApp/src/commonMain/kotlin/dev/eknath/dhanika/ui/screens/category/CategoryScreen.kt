package dev.eknath.dhanika.ui.screens.category

import androidx.compose.foundation.background
import kotlinx.datetime.Clock
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
import dev.eknath.dhanika.room.models.LocalCategory
import dev.eknath.dhanika.ui.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(viewModel: CategoryViewModel) {
    val categories by viewModel.categories.collectAsState()
    val isLoading by viewModel.isLoading
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Category")
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
                text = "Categories",
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
                    items(categories) { category ->
                        CategoryItem(
                            category = category,
                            onDelete = { viewModel.deleteCategory(category) }
                        )
                    }
                }
            }
        }

        if (showAddDialog) {
            AddCategoryDialog(
                onDismiss = { showAddDialog = false },
                onSave = { category ->
                    viewModel.saveCategory(category, onSuccess = {
                        showAddDialog = false
                    })
                }
            )
        }
    }
}

@Composable
fun CategoryItem(category: LocalCategory, onDelete: () -> Unit) {
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
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(category.color), shape = RoundedCornerShape(20.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = getCategoryTypeName(category.accountType),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Category",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AddCategoryDialog(onDismiss: () -> Unit, onSave: (LocalCategory) -> Unit) {
    var categoryName by remember { mutableStateOf("") }
    var accountType by remember { mutableStateOf(1) } // 1 = Income, 2 = Expense
    var color by remember { mutableStateOf(0xFF2196F3L) }

    val colorOptions = listOf(
        0xFF2196F3L, 0xFFF44336L, 0xFF4CAF50L, 0xFFFF9800L,
        0xFF9C27B0L, 0xFF00BCD4L, 0xFFFFEB3BL, 0xFF795548L
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Category") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = categoryName,
                    onValueChange = { categoryName = it },
                    label = { Text("Category Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Category Type:", style = MaterialTheme.typography.labelMedium)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = accountType == 1,
                        onClick = { accountType = 1 },
                        label = { Text("Income") }
                    )
                    FilterChip(
                        selected = accountType == 2,
                        onClick = { accountType = 2 },
                        label = { Text("Expense") }
                    )
                    FilterChip(
                        selected = accountType == 3,
                        onClick = { accountType = 3 },
                        label = { Text("Other") }
                    )
                }

                Text("Color:", style = MaterialTheme.typography.labelMedium)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    colorOptions.forEach { colorOption ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    Color(colorOption),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .then(
                                    if (color == colorOption) {
                                        Modifier
                                    } else {
                                        Modifier
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = { color = colorOption }) {
                                if (color == colorOption) {
                                    Text("✓", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSave(
                        LocalCategory(
                            categoryId = Clock.System.now().toEpochMilliseconds(),
                            name = categoryName,
                            accountType = accountType,
                            color = color
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

fun getCategoryTypeName(type: Int): String {
    return when (type) {
        1 -> "Income"
        2 -> "Expense"
        3 -> "Other"
        else -> "Unknown"
    }
}
