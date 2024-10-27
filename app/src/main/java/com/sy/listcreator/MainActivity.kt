package com.sy.listcreator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sy.listcreator.ui.theme.ListCreatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListCreatorTheme {
                val name = remember {
                    mutableStateOf("")
                }
                val names = remember {
                    mutableStateListOf<String>()
                }
                val isError = remember {
                    mutableStateOf(false)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        TextField(
                            value = name.value,
                            isError = isError.value,
                            colors = TextFieldDefaults.colors(
                                errorContainerColor = Color.Red.copy(
                                    alpha = 0.5f
                                )
                            ),
                            onValueChange = {
                                name.value = it
                            },
                            modifier = Modifier.weight(3f)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(onClick = {
                            if (name.value.isBlank()) {
                                isError.value = true
                            } else {
                                names.add(name.value)
                                name.value = ""
                                isError.value = false
                            }

                        }, modifier = Modifier.weight(1f)) {
                            Text(text = "Add")
                        }
                    }

                    AnimatedVisibility(visible = !names.isEmpty()) {

                        LazyColumn {
                            items(names) { name ->
                                TextButton(
                                    onClick = {
                                        names.remove(name)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}