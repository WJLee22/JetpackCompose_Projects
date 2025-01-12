package com.example.chapter5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.chapter5.ui.theme.Chapter5Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun BoxContainer() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly, //SpaceEvenly 배치: 시작과 끝부분&자식요소들 사이에 동일한 간격을 두고 배치

        horizontalAlignment = Alignment.CenterHorizontally //CenterHorizontally 정렬: 수평 중앙에 배치
    ) {
        DummyBox()
        DummyBox()
        DummyBox()

    }
}

@Composable
fun DummyBox(modifier: Modifier = Modifier) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    val randomColor = Color(red, green, blue)
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

}