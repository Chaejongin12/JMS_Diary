import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.jms_diary.R
import com.example.jms_diary.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DataBinding을 설정하고, layout 파일을 기반으로 binding 객체를 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info)

        // MainActivity에서 전달한 데이터를 Intent에서 추출
        val diaryTitle = intent.getStringExtra("diaryTitle")
        val diaryImage = intent.getStringExtra("diaryImage")
        val diaryContent = intent.getStringExtra("diaryContent")

        // 레이아웃에 데이터를 표시
        binding.diaryTitle.text = diaryTitle
        // 이미지 로딩 라이브러리인 Glide를 사용하여 이미지를 로드하고 이미지뷰에 표시
        Glide.with(this).load(diaryImage).into(binding.image)
        binding.content.text = diaryContent
    }
}
