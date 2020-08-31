package com.example.a2020_esc_study02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.json.JSONException


/*
* movie data class 만들기
* 영화 정보를 담은 arraylist를 만들기
* recyclerView Adapter 만들기
*/

/*
* 0.manifest에 internet 허용 적용 + volley,glide,gson 라이브러리 추가
* 1. volley 라이브러리를 이용하여 웹에서 현재 상영중인 영화 정보를 받아오기
* 2. gson 라이브러리를 이용하여 json을 data class로 바꾸기
* 3. glide 라이브러리로 url로부터 이미지 받아와서 imageview에 적용
* */

class MovieListActivity : AppCompatActivity() {

    /*//영화정보를 담고 있는 arraylist 만들기
    val movielist: ArrayList<Movie> = arrayListOf(
        Movie("테넷", 22.433, "시간의 흐름은...", "2020-08-20", R.drawable.m2),
        Movie("소년 시절에 너", 17.321, "넌 세상을 지켜...", "2020-07-09", R.drawable.m4),
        Movie("덩케르크 이스케이프", 15.842, "역사에 기록되지 않은...", "2020-08-03", R.drawable.m3),
        Movie("짱구는 못말려 : 신혼여행 허리케인", 10.954, "짱구 THE movie...", "2020-08-07", R.drawable.m1)

    )*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        //volley에 requestQueue 선언, volley를 쓸려면 requestQueue를 만들고 add로 호출해줘야한다.
        var requestQueue: RequestQueue = Volley.newRequestQueue(this)

        //gson 객체 선언
        var gson: Gson = Gson()
        //api 주소 선언
        var url =
            "https://api.themoviedb.org/3/movie/now_playing?" + "api_key=0dd5f4034d5d90f69d2da2015f4791b6" + "&language=ko-KR" + "&region=KR"

        //api 호출
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                //정상적으로 처리되었을 때 처리 코드
                //받아오 json을 toast로 출력
                //response : api 호출을 통해 넘어온 데이터
                    response ->
                try {
                    //response가 정상적으로 넘어온 경우
                   //json 데이터를 movielist data classs로 변형
                  //  Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()

                    val data : MovieList = gson.fromJson<MovieList>(response.toString(),MovieList::class.java)

                    //adapter 선언
                    val adapter = MovieAdapter(this, data.results)
                    //recyclerview에 만든 adapter를 세팅
                    movieRecycler.adapter = adapter

                    //linearLayoutManager 선언팅 : recyclerView의 방향을 결정, 위에서 아래나 왼쪽에서 오른쪽 등등
                    val lm = LinearLayoutManager(this)
                    //recyclerView에 LiinearLayoutManager 세팅
                    movieRecycler.layoutManager = lm



                } catch (e: JSONException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()

                }

            },

            Response.ErrorListener {
                //데이터가 비정상적으로 처리되었을 때 처리 코드
                error-> Toast.makeText(this,error.localizedMessage,Toast.LENGTH_SHORT).show()

            }
        )
        requestQueue.add(request)



/*
4강에서는 위치가 달라짐.
        //adapter 선언
        val adapter = MovieAdapter(this, movielist)
        //recyclerview에 만든 adapter를 세팅
        movieRecycler.adapter = adapter

        //linearLayoutManager 선언팅 : recyclerView의 방향을 결정, 위에서 아래나 왼쪽에서 오른쪽 등등
        val lm = LinearLayoutManager(this)
        //recyclerView에 LiinearLayoutManager 세팅
        movieRecycler.layoutManager = lm

*/

    }
}
