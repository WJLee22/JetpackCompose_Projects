package com.example.chapter1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chapter1.ui.theme.Chapter1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter1Theme {

                Greeting(
                    "반갑습니다!"

                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                shape = CircleShape,
                containerColor = Color.Yellow
            ) {

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
                contentColor = Color.Magenta,
                containerColor = Color(0xFFC6EEB1),

                ) {
                Text("BottomAppBar!")
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }
        },


        ) {
        // Scaffold의 content 영역에 innerPadding 적용
        // Compose 업데이트 이후, Scaffold의 content 영역에 padding values를 써주도록 변경되었음.
        // Scaffold의 content 영역에 배치되는 컴포저블들은 Scaffold 내부의 padding 값을 고려하여 배치되어야 함.
        // innerPadding을 적용해야 하단바, 상단바 등등의 요소들과 겹치지 않게돠어 화면에 보임.
            innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            MyComposableView() // innerPadding을 MyComposableView에 전달
        }
    }
}

@Composable
fun MyRowItem() {
    Log.d("TAG", "MyRowItem: ")
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(Color(0xFFeaffd0))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            color = Color.Blue,
            text = "안녕하세요?!",
            modifier = Modifier
                .padding(10.dp)
                .background(Color(0xFFffccbc))
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "안녕하세요?!")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "안녕하세요?!")
    }
}


@Composable
fun MyComposableView() {
    Log.d("TAG", "MyComposableView")

    Column(
        Modifier
            .background(Color.Green)
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 0..30) {
            MyRowItem()
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter1Theme {
        Greeting("안드로이드")

    }
}


