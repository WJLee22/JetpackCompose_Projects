package com.example.chapter7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chapter7.ui.theme.Chapter7Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Chapter7Theme {
                TextContainer()
            }
        }
    }
}

@Composable
fun TextContainer() {
    val name = "WjLee"
    val words = stringResource(R.string.dummy_short_test)
    var wordArray = words.split(" ")

    // 텍스트 목록을 저장할 상태 변수
    val textList = remember { mutableStateListOf<String>() }

    // Random 색상을 remember로 기억 (클릭텍스트로 인해 재렌더링 일어나더라도 기존 색상이 유지되도록 Color 객체를 기억해두기.)
    val randomColors = remember {
        // map 함수는 컬렉션(예: 배열, 리스트)의 각 요소에 특정 연산(transform람다 내부에 기술)을 적용하여 새로운 컬렉션을 생성하는 함수
        // 여기서는,map 함수을 사용하여 wordArray의 length만큼 순환하면서 Color 객체를 생성하고, 이를 randomColors에 저장
        wordArray.map {
            Color(Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255))
        }
    }
    // rememberScrollState: 스크롤 상태를 기억하는 함수. 스크롤 위치를 저장하고, 스크롤 위치를 변경할 때마다 스크롤 상태를 업데이트.
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Text(
            // buildAnnotatedString: AnnotatedString을 생성하는 빌더 함수. buildAnnotatedString를 사용하면 텍스트의 각 부분에 원하는 스타일을 자유롭게 적용할 수 있음.
            // AnnotatedString: 텍스트의 일부분에 각기 다른 스타일(색상, 폰트, 크기 등)을 적용할 수 있게 해주는 클래스.
            // withStyle: buildAnnotatedString 안에서 특정 텍스트 범위에 스타일을 적용할 때 사용하는 함수.
            // spanStyle: 텍스트의 스타일을 지정하는 클래스. 색상, 글꼴, 크기 등을 지정할 수 있음.

            // withStyle을 사용하여 스타일을 지정하고(SpanStyle을 통해) -> withStyle의 람다 함수 내부에 append를 사용하여 텍스트를 추가하면 -> append 함수로 추가된 텍스트에는 withStyle에서 지정한 스타일이 적용됨.
            // buildAnnotatedString는 이 정보들을 바탕으로 최종적으로 AnnotatedString을 생성

            text = buildAnnotatedString {

                // Brush.linearGradient: 선형 그라데이션을 생성하는 함수. 여러 색상을 지정하면 그 색상들을 선형으로 연결한 그라데이션을 생성.
                withStyle(
                    style = SpanStyle(
                        brush = Brush.linearGradient(
                            listOf(
                                Color.Red,
                                Color(255, 165, 0),
                                Color.Green,
                                Color.Blue,
                                Color(75, 0, 130),
                                Color(238, 130, 238)
                            )
                        )
                    )
                ) {
                    append("안녕하세요? 반갑습니다!\n")
                }
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold))
                {
                    append("오늘도 좋은하루되세요!\n")
                }

                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontFamily = FontFamily(Font(R.font.bamin_pro))
                    )
                )
                {
                    append("감사합니다~!")
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5ECEC)),

            style = TextStyle(
                textAlign = TextAlign.Start,
            ),

            fontSize = 20.sp,
            lineHeight = 30.sp
        )

        Text(
            text = "안녕하세요? 반갑습니다! ${name}",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            style = TextStyle(
                textAlign = TextAlign.Center,
            ),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )

        Text(
            text = stringResource(id = R.string.dummy_short_test),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            style = TextStyle(
                //Justify: 텍스트를 양쪽 맞춤으로 정렬
                textAlign = TextAlign.Justify,
                // 데코레이션: 밑줄, 취소선 등 텍스트에 추가적인 장식을 부여
                // combine: 여러 데코레이션을 리스트로 받아서 결합
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline
                    )
                ),
            )
        )

        Text(
            text = buildAnnotatedString {
                wordArray.forEachIndexed { index, word ->
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = randomColors[index]
                        )
                    ) {
                        append("${word} ")
                    }
                }
            }
        )

        Text(
            text = stringResource(id = R.string.dummy_short_test),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            style = TextStyle(
                textAlign = TextAlign.Start,
                // fontFamily: 텍스트의 글꼴을 지정, 여기서는 배달의 민족 꾸부림체 사용.
                fontFamily = FontFamily(Font(R.font.bamin_ggu)),
            ),
            fontSize = 18.sp,
            // lineHeight: 텍스트의 줄 간격을 지정
            lineHeight = 30.sp
        )

        Text(
            text = stringResource(id = R.string.dummy_long_test),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            style = TextStyle(
                textAlign = TextAlign.Start,
            ),
            // maxLines: 텍스트의 최대 행 수를 지정
            maxLines = 3,
            // overflow: 텍스트가 표시 영역을 벗어났을 때 처리 방법을 지정.
            // Ellipsis: 텍스트가 표시 영역을 벗어났을 때 생략 부호(...)를 표시
            overflow = TextOverflow.Ellipsis
        )

        ClickableText(
            text = AnnotatedString("클릭!"), onClick = {
                textList.add("클릭되었습니다~")
            }, style = TextStyle(
                color = Color.Blue,
                fontSize = 20.sp
            )
        )

        // 텍스트 목록을 표시
        textList.forEach { text ->
            Text(text = text, modifier = Modifier.padding(10.dp))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter7Theme {
        TextContainer()
    }
}

