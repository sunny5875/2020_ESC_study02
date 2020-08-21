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

/*
* data class와 recyclerView adapter를 만들 것임
* */

data class Movie(
    val title: String,
    val popularity: Double,
    val description: String,
    val openDate: String,
    //drawable에서 이미지 가져올려면 int여야 한다.
    val posterUrl: Int
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
            imgPoster.setImageResource(movie.posterUrl)
            tvTitle.text=movie.title
            tvDescription.text="설명 : "+movie.description
            tvPopularity.text="인기도 : "+ movie.popularity
            tvOpenDate.text="개봉일 : "+movie.openDate


            //container를 눌렀을 떄 토스트 메세지가 나오도록 만든 코드
            container.setOnClickListener{
                Toast.makeText(context,movie.title,Toast.LENGTH_SHORT).show()
            }

        }

    }

}