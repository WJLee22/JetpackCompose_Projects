package com.example.chapter1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.chapter1.ui.theme.Chapter1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Chapter1Theme {

                Greeting(
                    "이원준입니다!"

                )

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { },
                shape = CircleShape,
                containerColor = Color.Yellow) {

                Text("클릭")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("WJ's Android App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF25287),
                )
            )
        },

        bottomBar = {
            BottomAppBar(
                contentColor = Color.Red,
                containerColor = Color(0xFF00FF00),

                ) {
                Text("BottomAppBar")
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }
        },


        ) {
        MyComposableView()
    }
}

@Composable
fun MyComposableView() {
    Log.d("TAG", "MyComposableView")
    Row {
        Text("Hello")
        Text("World")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter1Theme {
        Greeting("안드로이드")

    }
}


