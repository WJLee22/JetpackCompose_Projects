package com.example.chapter4

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.chapter4.ui.theme.Chapter4Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter4Theme {
                VerticalContainer()
            }
        }
    }
}

@Composable
fun VerticalContainer() {
    //Column 레이아웃 컴포저블: 자식 요소들을 수직으로 배치하는 레이아웃.
    //Column의 핵심적인 속성은 verticalArrangement와 horizontalAlignment.


    //📌verticalArrangement: 자식 요소들을 수직 기준으로 어떻게 배치할지 결정. 기본값은 Top.
    // 컨테이너가 Column이기 때문에 verticalArrangement을 사용하여 수직상으로 어디에 배치할지 결정.

    // Arrangement.Top: 시작점을 기준으로 배치(Column 레이아웃의 상단에 붙어서 배치됨)
    // Arrangement.Bottom: 끝점을 기준으로 배치(하단에 붙어서 배치됨)
    // Arrangement.Center: 수직 중앙에 배치
    // Arrangement.SpaceAround: 자식요소들 상하측 주변에 동일한 간격을 두고 배치(요소들의 상-하 양 끝에 일정 간격이 생김)
    // Arrangement.SpaceBetween: 시작점(상단)과 끝점(하단)에 각각 붙어서 배치(양 끝에 붙어서 배치됨. 내부 요소들은 동일한 간격으로 배치됨)
    // Arrangement.SpaceEvenly: <시작부분 여백 & 끝부분 여백 & 자식요소들 사이 여백> 모두 동일한 간격을 두고 배치

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //📌horizontalAlignment: 자식 요소들을 수평 기준으로 어떻게 정렬할지 결정. 기본값은 Start.
    // 컨테이너가 Column이기 때문에 horizontalAlignment을 사용하여 수평상으로 어디에 배치할지 결정.

    // Alignment.Start: 시작점(Column의 좌측)을 기준으로 배치(자식 요소들이 Column의 좌측벽에 딱 붙어서 배치됨)
    // Alignment.End: 끝점(Column의 우측)을 기준으로 배치(자식 요소들이 Column의 우측벽에 딱 붙어서 배치됨)
    // Alignment.CenterHorizontally: 중앙을 기준으로 배치(중앙 배치)


    Column(

        modifier = Modifier
            .background(Color.White)
            //fillMaxSize: 컴포저블이 부모 컴포저블의 크기를 최대한 채우도록 지시. 가로, 세로 모두 최대 크기로 채움.
            //여기서는, Chapter3Theme이 Container()의 부모 역할을 하고, Container() 안에 있는 Column은 Chapter3Theme을 최종적인 부모로 갖는다.
            //Chapter3Theme은 기본적으로 화면 전체를 채우도록 설계되어 있음. 따라서, Column은 화면 전체를 채우게된다.
            //Chapter3Theme은 Material Design 컴포넌트를 제공하는 테마 컴포저블이자 최상위 컴포저블. 그 안에 Container()가 있고, Container() 안에 Column이 있음.
            .fillMaxSize(),

        verticalArrangement = Arrangement.SpaceEvenly, //SpaceEvenly 배치: 시작과 끝부분&자식요소들 사이에 동일한 간격을 두고 배치

        horizontalAlignment = Alignment.CenterHorizontally //CenterHorizontally 정렬: 수평 중앙에 배치
    ) {
        DummyBox ()
        DummyBox ()
        DummyBox ()

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
    Chapter4Theme {
        VerticalContainer()
    }
}