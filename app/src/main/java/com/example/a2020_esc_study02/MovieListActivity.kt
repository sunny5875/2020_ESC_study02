package com.example.a2020_esc_study02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_list.*


/*
* movie data class 만들기
* 영화 정보를 담은 arraylist를 만들기
* recyclerView Adapter 만들기
*/

class MovieListActivity : AppCompatActivity() {

    //영화정보를 담고 있는 arraylist 만들기
    val movielist : ArrayList<Movie> = arrayListOf(
            Movie("테넷",22.433,"시간의 흐름은...","2020-08-20",R.drawable.m2),
        Movie("소년 시절에 너",17.321,"넌 세상을 지켜...","2020-07-09",R.drawable.m4),
        Movie("덩케르크 이스케이프",15.842,"역사에 기록되지 않은...","2020-08-03",R.drawable.m3),
        Movie("짱구는 못말려 : 신혼여행 허리케인",10.954,"짱구 THE movie...","2020-08-07",R.drawable.m1)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        //adapter 선
        val adapter= MovieAdapter(this,movielist)
        //recyclerview에 만든 adapter를 세팅
        movieRecycler.adapter = adapter

        //linearLayoutManager 선언팅 : recyclerView의 방향을 결정, 위에서 아래나 왼쪽에서 오른쪽 등등
        val lm = LinearLayoutManager(this)
        //recyclerView에 LiinearLayoutManager 세팅
        movieRecycler.layoutManager=lm


    }
}
