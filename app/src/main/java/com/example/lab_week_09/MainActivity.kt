package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.lab_week_09.ui.theme.OnBackgroundTitleText
import com.example.lab_week_09.ui.theme.OnBackgroundItemText
import com.example.lab_week_09.ui.theme.PrimaryTextButton
// Declare a data class called Student
data class Student(
    val name: String
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Hapus val list = listOf("Tanu", "Tina", "Tono")
                    Home() // Panggil Home tanpa parameter
                }
            }
        }
    }
}



@Composable
fun Home() {
    // Di sini, kita membuat mutable state List of Student
    val listData = remember {
        mutableStateListOf(
            Student("Tanu"),
            Student("Tina"),
            Student("Tono")
        )
    }
    // Di sini, kita membuat mutable state of Student untuk input field
    var inputField by remember { mutableStateOf(Student("")) }

    // Hapus Home Content Lama, dan Panggil HomeContent dengan State dan Lambda Functions
    HomeContent(
        listData,
        inputField,
        onInputValueChange = { input -> inputField = inputField.copy(name = input) },
        onButtonClick = {
            // == ASSIGNMENT NO. 1 FIX: Mencegah String Kosong ==
            if (inputField.name.isNotBlank()) {
                listData.add(inputField.copy()) // Gunakan .copy() untuk menambahkan objek baru
            }
            inputField = Student("") // Reset input field
        }
    )
}

@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gunakan OnBackgroundTitleText
                OnBackgroundTitleText(text = stringResource(id = R.string.enter_item))

                TextField(
                    value = inputField.name,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    onValueChange = {
                        onInputValueChange(it)
                    }
                )
                // Gunakan PrimaryTextButton
                PrimaryTextButton(
                    text = stringResource(id = R.string.button_click),
                    onClick = onButtonClick
                )
            }
        }
        items(listData) { item ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gunakan OnBackgroundItemText
                OnBackgroundItemText(text = item.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    // Home content preview
    HomeContent(
        listData = mutableStateListOf(Student("Tanu"), Student("Tina"), Student("Tono")),
        inputField = Student(""),
        onInputValueChange = {},
        onButtonClick = {}
    )
}