package com.example.jms_diary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.jms_diary.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info)

        val diaryTitle = intent.getStringExtra("diaryTitle")
        val diaryImage = intent.getStringExtra("diaryImage")
        val diaryContent = intent.getStringExtra("diaryContent")

        binding.diaryTitle.text = diaryTitle
        Glide.with(this).load(diaryImage).into(binding.image)
        binding.content.text = diaryContent
    }
}
