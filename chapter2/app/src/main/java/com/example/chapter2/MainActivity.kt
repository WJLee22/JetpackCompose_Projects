package com.example.chapter2

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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
        items(items = randomUsers) { randomUser ->
            RandomUserView(randomUser)
        }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser) {
    //MaterialTheme.typography를 사용하여 텍스트 스타일을 지정. MaterialTheme.typography는 Material Design에 정의된 텍스트 스타일을 제공.
    //Type.kt에서 원하는대로 커스텀하여 사용할 수도 있음. -> 이 예제에서는 bodySmall과 bodyLarge 스타일을 커스텀하여 사용.
    val typography = MaterialTheme.typography
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        //Card 컴포저블의 elevation 속성에는 Dp 타입이 아닌 CardElevation 타입의 값을 지정하도록 변경됨.
        //CardElevation을 사용하려면 CardDefaults.cardElevation() 함수를 사용하여 CardElevation 객체를 생성해야 함.
        // elevation 속성은 카드가 떠있는 정도를 나타냄.
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        //카드 모서리 둥글게.
        shape = RoundedCornerShape(12.dp),

        )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            //내부 컴포저블들 수직 정렬
            verticalAlignment = Alignment.CenterVertically,
            // 내부 컴포저블들 간의 간격 지정
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
/*            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 60.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            )*/

            //프로필 이미지를 표시하는 ProfileImg 컴포저블을 사용하여 프로필 이미지 표시.
            ProfileImg(imgUrl = randomUser.profileImage)

            Column() {
                Text(
                    text = randomUser.name,
                    //material 2 시절에는 typography에 직접 타이포그레피 정의가 가능했지만, material 3에서는 기존에 있는 상수형 속성들만 사용가능해짐.
                    style = typography.bodySmall
                )
                Text(
                    text = randomUser.description,
                    style = typography.bodyLarge
                )
            }
        }
    }

}


@Composable
fun ProfileImg(imgUrl: String, modifier: Modifier = Modifier) {
    //이미지 비트맵

    // MutableState 객체는 Compose에서 상태를 저장하고 관리하는 데 사용되는 특별한 객체. 이 객체는 value라는 프로퍼티를 가지고 있으며, 이 프로퍼티에 상태 값이 저장됨.
    // mutableStateOf() 함수: 상태 객체를 생성 & 반환. 상태 객체는 상태 값을 저장하고, 상태 값이 변경되면 UI업데이트 트리거 -> Composable 함수를 다시 호출하여 UI를 업데이트함.
    // remember: 컴포저블 함수가 다시 호출(재 컴포지션, UI 업데이트, UI재구성)되더라도 상태 값이 초기화되지 않고 이전 값을 유지할 수 있도록 해줌.
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }

   // 이미지 뷰의 크기와 모양을 지정하기 위한 modifier
    val imageModifier = modifier
        .size(50.dp)
        .clip(CircleShape)

    //Glide 라이브러리를 통해서 원격 이미지에 있는 비트맵을 다운로드받고 그 비트맵을 이미지뷰에 넣는식.
    //Glide.with() 함수는 Glide 인스턴스를 생성. Glide.with() 함수는 인자로 LocalContext.current로 현재 컨텍스트를 받음.
    //asBitmap().load(source): 해당 이미지를 비트맵으로 로드.
    //into(target): 비트맵 로드가 완료되면 해당 비트맵을 어디에 표시할지 지정.
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>() { //CustomTarget: Glide에서 제공하는 비트맵 로드 콜백 인터페이스
            //onResourceReady(): 비트맵 로드가 완료되면 호출되는 콜백 메서드.
            //resource 매개변수: 다운로드 받은 비트맵 이미지 파일 추출물.
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource //Glide 라이브러리를 통해 다운로드받은 비트맵을 상태 값으로 저장.
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    // 이제, 이 추출해낸 비트맵을 Image 컴포저블을 사용하여 화면에 표시.

    // if 비트맵이 정상적으로 존재한다면,
    // 비트맵을 ImageBitmap(이미지 비트맵)으로 변환하고, Image 컴포저블을 사용하여 화면에 표시.
    bitmap.value?.asImageBitmap()?.let {
        Image(
            bitmap = it, //최종 추출물인 비트맵 이미지를 Image 컴포저블에 전달하여 화면에 이미지 표시.
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    }
    // else 만약 비트맵이 존재하지 않는다면, 기본 이미지를 표시.
        ?: Image(
        painter = painterResource(R.drawable.ic_empty_user_img),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter2Theme {
        ContentView()
    }
}