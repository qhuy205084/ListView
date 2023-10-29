package com.example.listview_20231029.datas

import android.util.Log

// 필요한 데이터 클래스는 별도 클래스를 만들어 관리.

class StudnetData(
    val name: String,
    val birthYear: Int,
    val phoneNum: String) {
    // stundentData의 생성자 변경 , 이름 , 출생년도 , 전화번호를 넣어서 생성하도록 변경

    // 생성자 문법 체험 -> 전화번호 없는 생성자도 쓰고싶을때 , MAIN은 전부 받음 , SUB는 전화번호만 없을때
    constructor(name:String , birthYear: Int) : this(name, birthYear,"전화번호 모름")

    // 현재 한국식 나이(만나이)를 계산해서 리턴해주는 함수
    //  : <-- Int 처럼 이게 리턴 타입임.
    fun getKoreanAge(year:Int) : Int{
        return year - this.birthYear + 1
    }

    fun getSimplePhoneNum(){ // : int 처럼 리턴타입이 안써있으면 , 리턴 없음(자바의 void 느낌)
        Log.d("학생데이터",this.phoneNum.replace("-",""))
    }
}