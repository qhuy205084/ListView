package com.example.listview_20231029

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.listview_20231029.adapters.StudnetAdapter
import com.example.listview_20231029.databinding.ActivityMainBinding
import com.example.listview_20231029.datas.StudnetData

class MainActivity : AppCompatActivity() {
    // 어탭터 세팅
    lateinit var mStdAdapter: StudnetAdapter

    lateinit var binding: ActivityMainBinding

    // Arraylist 쓰기 위한 세팅
    val mStudentList = ArrayList<StudnetData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 어댑터 바인딩 작업                여기      ,        어디 xml인지         , 어디 Arraylist
        mStdAdapter = StudnetAdapter(this , R.layout.student_list_item , mStudentList)
        
        // 어탭터 세팅
        binding.studnentListView.adapter = mStdAdapter

//        화면이 켜질때, 학생 목록을 ArrayList에 수기로 추가(임시 방안임.)

        // Arraylist 내용 추가 , 생성자 규칙에 따라서
        mStudentList.add(StudnetData("조영재", 1999, "010-1234-5678"))
        mStudentList.add(StudnetData("박진우", 1999, "010-7777-7777"))
        mStudentList.add(StudnetData("우승진", 1999, "010-5877-8585"))
        mStudentList.add(StudnetData("임홍인", 1999, "010-1010-2350"))
        mStudentList.add(StudnetData("테스트", 2000))
        mStudentList.add(StudnetData("가나다", 2001, "010-1234-5678"))
        mStudentList.add(StudnetData("연양갱", 2002, "010-7777-7777"))
        mStudentList.add(StudnetData("맛동산", 2003, "010-5877-8585"))
        mStudentList.add(StudnetData("예감", 2004, "010-1010-2350"))
        mStudentList.add(StudnetData("빈츠", 2005))


        // 한명의 학생을 클릭하면 -> 토스트로 "이름 : 연락처"를 토스트 메시지로 출력하기
        binding.studnentListView.setOnItemClickListener { adapterView, view, position, l ->
            // 이 함수의 세번째 = i,position 변수를 클릭된 '위치'를 알려주는 역할.
            // mStudnetList 중에서 클릭된 위치에 맞는 학생 추출 -> 활용
            val clickedStd = mStudentList[position]

            Toast.makeText(this, "${clickedStd.name} : ${clickedStd.phoneNum}", Toast.LENGTH_SHORT).show()
        }
        
        // 한명의 학생을(하나의 아이템을) 오래 클릭하면 선택된 학생을 목록에서 삭제하는 내용
        binding.studnentListView.setOnItemLongClickListener { adapterView, view, position, l ->

            val std = mStudentList[position]

            // 경고창을 띄워서 확인 받고 나서 
            val alert = AlertDialog.Builder(this)
            alert.setTitle("삭제 확인")
            alert.setMessage("정말 ${std.name}학생을 삭제하겠습니까?")
            alert.setPositiveButton("확인",DialogInterface.OnClickListener { dialogInterface, i -> 
                // 확인 버튼을 누르면 할 일 이벤트 넣음
                mStudentList.removeAt(position) // 내용물 변경

                // 어댑터에게 통보
                mStdAdapter.notifyDataSetChanged()
            })
            alert.setNegativeButton("취소",null)
            alert.show() // 이 코드가 없으면 alert가 안뜸

            // 오래 클릭된 학생은 목록 삭제
//            mStudentList.removeAt(position)

            return@setOnItemLongClickListener true
        }
    }
}