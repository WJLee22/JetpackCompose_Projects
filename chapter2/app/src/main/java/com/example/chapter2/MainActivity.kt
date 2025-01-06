package com.example.chapter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chapter2.ui.theme.Chapter2Theme
import com.example.chapter2.ui.theme.Purple80
import com.example.chapter2.utils.DummyDataProvider
import com.example.chapter2.utils.RandomUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Chapter2Theme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView() {
    Surface {
        Scaffold(
            containerColor = Color.White,
            topBar = {
                MyAppBar()
            },
        ) {
            //object 키워드로 선언된 싱글턴 객체인 DummyDataProvider의 userList를 인자로 전달
            RandomUserListView(DummyDataProvider.userList, modifier = Modifier.padding(it))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar() {
    TopAppBar(
        modifier = Modifier.height(58.dp),
        // 최신 Material 3에서는 TopAppBar의 색상을 변경하기 위해 TopAppBarDefaults.topAppBarColors() 함수를 사용해야 함.
    colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80),
        title = {
            Text(
                text = stringResource(id = R.string.my_app_name),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                lineHeight = 58.sp // 텍스트 높이를 앱 바 높이와 동일하게 설정하여 수직 중앙 정렬
            )
})
}

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>, modifier: Modifier) {
    //일반적인 Column은 모든 아이템을 한 번에 메모리에 로드하기 때문에 아이템 개수가 많아질수록 메모리 사용량이 증가하고 성능이 저하될 수 있음.
    //하지만 LazyColumn은 화면에 보이는 아이템만 메모리에 로드하고, 화면에서 사라진 아이템은 메모리에서 제거합니다. 이러한 방식을 통해 메모리 사용량을 줄이고 성능을 향상시킬 수 있음
    //xml 시절의 RecyclerView와 비슷한 역할을 함. 화면에 보이는 일정 아이템들만 로드하고, 스크롤 시에만 새로운 아이템을 로드함
    LazyColumn(modifier = modifier) {
        //items(): 인자값으로 받은 리스트를 순회하면서 각 요소에 대해 람다식에 정의된 동작을 수행. LazyColumn은 items() 함수를 통해 효율적으로 리스트의 각 요소를 화면에 표시할 수 있는 것.
        items(items=randomUsers) {randomUser ->
            Text(text= randomUser.name) //
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter2Theme {
        ContentView()
    }
}