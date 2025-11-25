package dev.eknath.dhanika.ui.screens.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(viewModel: UserViewModel) {
    val nameTextField = mutableStateOf(TextFieldValue())
    val editMode = mutableStateOf(false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("User Info")
                },
            )
        }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text("Hi! ${viewModel.userInfo.value?.name ?: "User"}")

            Button(
                enabled = true,
                onClick = {
                    editMode.value = true
                    nameTextField.value =
                        TextFieldValue(text = viewModel.userInfo.value?.name.orEmpty())
                }) {
                Text("Edit")
            }

            if (editMode.value)
                Column {
                    TextField(
                        value = nameTextField.value,
                        onValueChange = { updateName -> nameTextField.value = updateName }
                    )

                    Button(
                        enabled = true,
                        onClick = {
                            viewModel.updateUserName(
                                nameTextField.value.text.orEmpty(),
                                onSuccess = {
                                    editMode.value = false
                                },
                                onError = {

                                })

                        }) {
                        Text("Update")
                    }

                }
        }

    }
}