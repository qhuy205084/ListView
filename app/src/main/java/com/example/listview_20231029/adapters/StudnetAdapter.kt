package com.example.listview_20231029.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.listview_20231029.R
import com.example.listview_20231029.datas.StudnetData

// 어댑터를 쓰는 이유 : 안드로이드 앱은 PC보다 메모리 환경이 적기 때문에
// 똑같은 요소를 붙여서 만들어버리면 앱 화면이 출력되기전에 OS에 의해 강제종료 되버림.

// 어탭터를 만들었으면 상속 부터 받자.

// 어떤 타입의 데이터를 받아올것인지 <> 로 설정해야함.
class StudnetAdapter(
    // ArrayAdapter는 기본 생성자를 지원 안함 , 파라미터 값을 넣어서 받아야함.
    // 어댑터 클래스가 생성자에게 부모(studentData)에게서 받아오게

    // ListView 커스텀 작업도 여기서 한다.
    val mContext: Context,
    val resID: Int,
    val mList: ArrayList<StudnetData>
) : ArrayAdapter<StudnetData>(mContext, resID, mList) {
    // 커스텀으로 만든 xml을 -> 코틀린에서 끌어와주는 객체 작성
    val inf = LayoutInflater.from(mContext)


    // 완성한 inf를 이용해서 xml을 가져오는 함수 작성
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                                    // View? <- ? 이거는 타입중에 null이 있을수도 있으니 감안하고 코딩해라 라는 의미

        // tempRow 역할은 리스트뷰의 "재 사용성"에서  나오는 "null" 가능성을 대비하기 위한 '임시' 변수
        var tempRow = convertView

        if (tempRow == null) {
                                                  // 레이아웃 ,  부가정보
            tempRow = inf.inflate(R.layout.student_list_item, null)
        }
        // row = xml을 그려낸 객체 변수
        val row = tempRow!!
        // if 문 빠져나온 후 , 원래 null 이면 -> 새로 그려서 넣어줌 , null이 아니라면 있는걸로 활용
        // tempRow에 null일 가능성이 제거됨.

        // xml을 그린 row 객체 내부에서 , TextView등 같은걸 찾아서 실제 데이터로 연동하는것
        // 실제 데이터 = 어떤 학생 , mList의 position에 맞는 객체

        val stdData = mList[position] // 그림을 그려줄 위치에 맞는 학생 객체 추출

        // row안에 이름 표시는 TextView를 변수로 가져와야 , stdData의 이름값을 대입가능
        // 어탭터 안에서는 데이터 바인딩 사용불가!!
        // 직접 코드로 불러와야함(텍스트 뷰들 아이디 같은거)

        val txtname = row.findViewById<TextView>(R.id.txtName)
        val txtPhoneNum = row.findViewById<TextView>(R.id.txtPhoneNum)
        val txtAge = row.findViewById<TextView>(R.id.txtAge)

        txtname.text = stdData.name
//        txtPhoneNum.text = stdData.phoneNum
//        txtPhoneNum.text = "{${stdData.getSimplePhoneNum()},{${stdData.phoneNum}}"
        txtPhoneNum.text = "${stdData.phoneNum}"

        // 계산이 가능하다.
//        txtAge.text = "(${2023 - stdData.birthYear + 1}세)"
        txtAge.text = "(${stdData.getKoreanAge(2023)})세"

        return row
    }
}