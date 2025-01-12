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
            Chapter5Theme {
                BoxContainer()
            }
        }
    }
}

@Composable
fun BoxContainer() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        //contentAlignment의 default값은 Alignment.TopStart.
        contentAlignment = Alignment.Center //Center 정렬: 모든 요소들 모아서 중앙에 배치
    ) {
        DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        DummyBox(modifier = Modifier.size(150.dp), color = Color.Yellow)
        DummyBox(color = Color.Blue)

    }
}



@Composable
fun DummyBox(modifier: Modifier = Modifier, color: Color? = null) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    //만약 외부에서 넘어오는 Color가 있다면 해당 값을 적용하고, 만약 color값이 null값이라면 -> 즉 없다면, 랜덤 색상을 적용.

    //?.: Safe Call 연산자. color 변수가 null이 아니면 let 함수를 호출하고, null이면 let 함수를 호출하지 않고 null을 반환
    //let { it }: let 함수는 람다 표현식을 인자로 받아서 실행하는 함수. 여기서는 color가 null이 아닐 경우, color 값을 it이라는 이름으로 람다 표현식 내부에서 사용할 수 있도록 전달하고, 람다 표현식의 결과를 반환. 이 경우, 람다 표현식은 단순히 it을 반환하므로, color 값이 그대로 반환
    //?:: 엘비스 연산자. 왼쪽 피연산자가 null이 아니면 왼쪽 피연산자를 반환하고, null이면 오른쪽 피연산자를 반환.
    // < ?.let{} ?: >  ?. 의 좌변 값이 null이 아니라면 우변인 let함수 실행하고(let 함수의 람다의 매개변수로 color가 it으로 넘어오고 이를 반환),< ?: > ?. 연산자가 null을 반환하고 -> 엘비스 연산자가 이어 받아서 처리. null이라면 우변값을 반환한다.
    // 즉, color가 null이 아니라면 color값을 그대로 대입하고, null이라면 랜덤 색상을 사용한다.
    val randomColor = color?.let{ it } ?: Color(red, green, blue)
    Box(
        modifier = modifier
            .size(100.dp)
            .background(randomColor),
        /*contentAlignment = Alignment.Center*/
    ) {

    }
}
fun add(x: Int, y: Int) { x + y }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BoxContainer()
}