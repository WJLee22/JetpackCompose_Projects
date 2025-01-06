package com.example.lazycolumn_prac

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumn_prac.ui.theme.LazyColumn_PracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            LazyColumn_PracTheme {

                var clickCount : MutableState<Int> = remember { mutableStateOf(0) }
                var messageList: SnapshotStateList<Message> = remember { mutableStateListOf<Message>()}
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(){
                        Greeting("Android", clickCount.value, onClick = {
                            Log.d("TAG", "onClick: 클릭됨")
                            clickCount.value += 1
                            val newMsg = Message(id = clickCount.value, content = "메시지 입니다 ${clickCount.value}")
                            messageList.add(newMsg)
                        })
                        MessageList(messageList, onDeleteClicked = {
                            Log.d("TAG", "onDeleteClicked: ${it.id}")
                            messageList.remove(it)
                        })
                    }
                }

                }
            }
        }
    }


@Composable
fun MessageList(messages: List<Message>, onDeleteClicked:(Message) -> Unit){
    //xml 시절의 RecyclerView와 비슷한 역할
    LazyColumn{// 마지막 매개변수는 content 람다식. LazyColumn 이 어떤 내용을 보여줄지 정의
        //items() 함수는 LazyColumn 이나 LazyRow 같은 Lazy 컴포저블 안에서 리스트의 각 항목을 어떻게 표시할지 지정하는 함수
        //1. items 함수는 먼저 화면에 표시할 항목들이 담긴 리스트를 입력으로 받음
        //2. items 함수는 리스트를 받아서 그 안에 있는 항목들을 하나씩 순회하며 사용자가 제공한 람다 함수에서 처리.

        items(count = messages.size) { index ->
            val msg = messages[index]
            MessageRow(msg, onDeleteClicked)
        }
    }
}

@Composable
fun MessageRow(msg: Message, onDeleteClicked:(Message) -> Unit){

    Surface(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 10.dp,
        border = BorderStroke(1.dp, Color.LightGray)
    ) { Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "id: ${msg.id} / content: ${msg.content}")
        Button(onClick = {onDeleteClicked(msg)}) {
            Text(text = "삭제")
        }
     }
    }
}

@Composable
fun Greeting(name: String, clickCount: Int, onClick: () -> Unit){
    Column() {
        Text(text = "Hello $name!")
        Text(text = "클릭 수: $clickCount")
        Button(onClick = onClick) {
            Text(text = "클릭")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyColumn_PracTheme {

        var clickCount : MutableState<Int> = remember { mutableStateOf(0) }
        var messageList: SnapshotStateList<Message> = remember { mutableStateListOf<Message>()}
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(){
                Greeting("Android", clickCount.value, onClick = {
                    Log.d("TAG", "onClick: 클릭됨")
                    clickCount.value += 1
                    val newMsg = Message(id = clickCount.value, content = "메시지 입니다 ${clickCount.value}")
                    messageList.add(newMsg)
                })
                MessageList(messageList, onDeleteClicked = {
                    Log.d("TAG", "onDeleteClicked: ${it.id}")
                    messageList.remove(it)
                })
            }
        }

    }
}
