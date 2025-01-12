package com.example.chapter6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chapter6.ui.theme.Chapter6Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter6Theme {
                BoxWithConstraintContainer()
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BoxWithConstraintContainer() {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 현재 이 컨테이너의 높이와 너비를 가져올 수 있다.

        // 디바이스의 세로-가로모드 마다 현재 이 컨테이너의 높이는 변경되기 때문에 이를 이용하여 조건을 걸어서 다른 색상의 뷰를 보여줄 수 있다.
        if (this.minHeight > 600.dp) {
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        } else {
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Yellow)
        }

        Text(text = "minHeight: ${this.minHeight}")

/*
        // 2개의 박스를 담는 컬럼
        Column() {
            BoxWithConstraintItem(modifier = Modifier
                .size(200.dp)
                .background(Color.Yellow))
            BoxWithConstraintItem(modifier = Modifier
                .size(300.dp)
                .background(Color.Green))
        }*/

    }
}

@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (this.minWidth > 200.dp) {
            Text(text = "이것은 큰 상자이다")
        } else {
            Text(text = "이것은 작은 상자이다")
        }
    }
}

@Composable
fun DummyBox(modifier: Modifier = Modifier, color: Color? = null) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    val randomColor = color?.let { it } ?: Color(red, green, blue)
    Box(
        modifier = modifier
            .size(100.dp)
            .background(randomColor)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter6Theme {
        BoxWithConstraintContainer()
    }
}