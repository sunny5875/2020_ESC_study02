package com.example.a2020_esc_study02

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//다른 엑티비티로 넘어가는 코드
        btnBoxOffice.setOnClickListener {
           val Intent= Intent(this,MovieListActivity::class.java)
            startActivity(Intent)
        }

//영화 웹홈페이지로 넘어가는 코드
        btnNaverMovie.setOnClickListener {
            val Intent= Intent(Intent.ACTION_VIEW,Uri.parse("https://movie.naver.com/"))
            startActivity(Intent)
        }
    }
}
