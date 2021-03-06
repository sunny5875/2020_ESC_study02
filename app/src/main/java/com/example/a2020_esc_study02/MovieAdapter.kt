package com.example.a2020_esc_study02

import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/*
* data class와 recyclerView adapter를 만들 것임
* */

//json에서 받아온 변수명과 인스턴스 변수의 이름이 같아야 하므로 같게 바꿔준다.
data class Movie(
    val title: String,
    val popularity: Double,
    val overview: String,
    val release_date: String,
    //drawable에서 이미지 가져올려면 int여야 한다.
    val poster_path: String
)


//[]로 묶여이쓴 이름으로 클래스르 만들어야 한다.
data class MovieList(
    val results: ArrayList<Movie>
)


//상속 대신 : 을 넣어 표현
//즉 , recylerView를 상속받고 있음

class MovieAdapter(val context: Context, val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.Holder>() {
    //함수 선언 : fun

    //cell 레이아웃을 불러오는 역할
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=LayoutInflater.from(context).inflate(R.layout.cell,parent,false)
        return Holder(view)

    }

    //셀 개수를 설정하는 역할, 셀 갯수를 반환하는 함수
    override fun getItemCount(): Int {
           return movieList.size

    }


//각셀에 맞는 정보를 넣는 역할
    override fun onBindViewHolder(holder: Holder, position : Int) {
        holder.bind(movieList[position])

    }
    //셀 그 자체
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        //셀 구성요소 가져옴
        val imgPoster= itemView.findViewById<ImageView>(R.id.imgPoster)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvPopularity = itemView.findViewById<TextView>(R.id.tvPopularity)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvOpenDate = itemView.findViewById<TextView>(R.id.tvOpenDate)
        val container= itemView.findViewById<ConstraintLayout>(R.id.container)

        //findViewById : view를 id값을 이ㅛㅇ해서 코드와 연결시키는 역할을 하는 함수
        //코틀린에서는 선택적으로 사용 가능

        //데이터를 셀의 레이아웃에 알맞게 넣어주는 역할
        fun bind(movie : Movie){
            val overview: String
            if(movie.overview.length>21){
                overview = movie.overview.slice(IntRange(0,20))+"..."
            }
            else{
                overview=movie.overview
            }
            //imgPoster.setImageResource(movie.poster_path)
            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movie.poster_path).into(imgPoster)
            tvTitle.text=movie.title
            tvPopularity.text="인기도 : "+ movie.popularity
            tvDescription.text="설명 : "+ overview
            tvOpenDate.text="개봉일 : "+movie.release_date


            //container를 눌렀을 떄 토스트 메세지가 나오도록 만든 코드
            container.setOnClickListener{
                Toast.makeText(context,movie.title,Toast.LENGTH_SHORT).show()
            }

        }

    }

}