package com.example.jms_diary

import InfoActivity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jms_diary.R
import com.example.jms_diary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // View들에 접근하기 위해 DataBinding을 사용
    private lateinit var binding: ActivityMainBinding
    // 갤러리에서 이미지를 가져오기 위한 요청 코드
    private val GET_GALLERY_IMAGE = 200
    // 이미지 URI를 저장할 변수
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DataBinding을 설정하고, layout 파일을 기반으로 binding 객체를 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 이미지를 선택하기 위한 갤러리 열기
        binding.image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            // 이미지 타입으로 필터링
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            // 갤러리 열기를 요청하고 결과를 받기 위해 startActivityForResult 사용
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }

        // 버튼을 눌렀을 때 동작하는 코드
        binding.button.setOnClickListener {
            // 제목에 입력된 텍스트 값을 가져옴
            val diaryTitle = binding.writeDiary.text.toString()
            // 이미지 URI를 문자열로 저장
            val diaryImage = selectedImageUri?.toString()
            // 내용에 입력된 텍스트 값을 가져옴
            val diaryContent = binding.editText.text.toString()

            // InfoActivity로 이동하면서 데이터를 넘겨주는 Intent 생성
            val intent = createInfoActivityIntent(diaryTitle, diaryImage, diaryContent)
            // 생성한 Intent를 사용하여 새로운 Activity로 이동
            startActivity(intent)
        }
    }

    // InfoActivity로 이동하면서 데이터를 넘겨주는 함수
    private fun createInfoActivityIntent(diaryTitle: String, diaryImage: String?, diaryContent: String): Intent {
        return Intent(this, InfoActivity::class.java).apply {
            putExtra("diaryTitle", diaryTitle)
            putExtra("diaryImage", diaryImage)
            putExtra("diaryContent", diaryContent)
        }
    }

    // 갤러리에서 이미지를 선택한 결과를 처리하는 콜백 메서드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 갤러리에서 이미지를 선택한 결과이면서 성공한 경우
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            // 선택된 이미지의 URI를 변수에 저장
            selectedImageUri = data.data
            // 이미지뷰에 선택된 이미지 표시
            binding.image.setImageURI(selectedImageUri)
        }
    }
}
