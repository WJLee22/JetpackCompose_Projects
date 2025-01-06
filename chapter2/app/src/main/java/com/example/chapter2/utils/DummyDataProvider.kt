package com.example.chapter2.utils

data class RandomUser(
    val name: String = "개발하는 WJ 👨‍🎓",
    val description: String = "오늘도 빡코딩 하고 게신가요?",
    val profileImage: String = "https://randomuser.me/api/portraits/women/74.jpg"
    )
object DummyDataProvider { //object 키워드 -> 싱글턴 객체 선언
    //200개의 요소를 담을 수 있는 RandomUser 타입의 리스트를 생성 & 각 요소를 람다식 실행 결과인 RandomUser 객체로 초기화
    val userList = List<RandomUser>(200){RandomUser()}
}