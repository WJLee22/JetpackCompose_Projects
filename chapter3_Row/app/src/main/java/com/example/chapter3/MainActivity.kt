package com.example.chapter3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chapter3.ui.theme.Chapter3Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter3Theme {
                Container()
            }
        }
    }
}

@Composable
fun Container() {
    //Row 레이아웃 컴포저블: 자식 요소들을 가로로 배치하는 레이아웃.
    //Row의 핵심적인 속성은 arrangement와 alignment.

    //📌arrangement: 자식 요소들을 수직or수평 기준으로 어떻게 배치할지 결정. 기본값은 Start. css의 flex와 비슷한 개념.
    //컨테이너가 Row이기 때문에 가로로 배치되니깐 horizontalArrangement(수평 배치)을 사용.

    // Arrangement.Start: 시작점을 기준으로 배치(좌측에 딱 붙어서 배치됨)
    // Arrangement.End: 끝점을 기준으로 배치(우측에 딱 붙어서 배치됨)
    // Arrangement.Center: 중앙을 기준으로 배치(중앙 배치)
    // Arrangement.SpaceBetween: 시작점과 끝점에 각각 붙어서 배치(양 끝에 붙어서 배치됨. 내부 요소들은 동일한 간격으로 배치됨)
    // Arrangement.SpaceAround: 자식요소들 좌우측 주변에 동일한 간격을 두고 배치(요소들의 양 끝에 일정 간격이 생김)
    // Arrangement.SpaceEvenly: 시작과 끝부분 여백&자식요소들 사이에 동일한 간격을 두고 배치

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //📌alignment: 자식 요소들을 수직or수평 기준으로 어떻게 정렬할지 결정. 기본값은 Top.
    // xml의 linearlayout의 gravity와 비슷한 개념. 즉, 내부 요소들을 어떻게 정렬할 것인지
    // 컨테이너가 Row이기 때문에 verticalAlignment을 사용하여 수직상으로 어디에 배치할지 결정.

    // Alignment.Top: 시작점을 기준으로 배치(상단에 붙어서 배치됨)
    // Alignment.Bottom: 끝점을 기준으로 배치(하단에 붙어서 배치됨)
    // Alignment.CenterVertically: 수직 중앙에 배치

    Row(

        modifier = Modifier
            .background(Color.White)
            //fillMaxSize: 컴포저블이 부모 컴포저블의 크기를 최대한 채우도록 지시. 가로, 세로 모두 최대 크기로 채움.
            //여기서는, Chapter3Theme이 Container()의 부모 역할을 하고, Container() 안에 있는 Row는 Chapter3Theme을 최종적인 부모로 갖는다.
            //Chapter3Theme은 기본적으로 화면 전체를 채우도록 설계되어 있음. 따라서, Row는 화면 전체를 채우게된다.
            //Chapter3Theme은 Material Design 컴포넌트를 제공하는 테마 컴포저블이자 최상위 컴포저블. 그 안에 Container()가 있고, Container() 안에 Row가 있음.
            .fillMaxSize(),

        horizontalArrangement = Arrangement.SpaceEvenly, //SpaceEvenly 배치: 시작과 끝부분&자식요소들 사이에 동일한 간격을 두고 배치

        verticalAlignment = Alignment.CenterVertically //CenterVertically 정렬: 수직 중앙에 배치
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
    Chapter3Theme {
        Container()
    }
}