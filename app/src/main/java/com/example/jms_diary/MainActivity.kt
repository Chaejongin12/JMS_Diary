package com.example.jms_diary

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jms_diary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val GET_GALLERY_IMAGE = 200
    private var selectedImageUri: Uri? = null // 이미지 URI를 저장할 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }

        binding.button.setOnClickListener {
            val diaryTitle = binding.writeDiary.text.toString()
            val diaryImage = selectedImageUri?.toString() // 이미지 URI를 문자열로 저장
            val diaryContent = binding.editText.text.toString()

            val intent = createInfoActivityIntent(diaryTitle, diaryImage, diaryContent)
            startActivity(intent)
        }
    }

    private fun createInfoActivityIntent(diaryTitle: String, diaryImage: String?, diaryContent: String): Intent {
        return Intent(this, InfoActivity::class.java).apply {
            putExtra("diaryTitle", diaryTitle)
            putExtra("diaryImage", diaryImage)
            putExtra("diaryContent", diaryContent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            binding.image.setImageURI(selectedImageUri)
        }
    }
}
