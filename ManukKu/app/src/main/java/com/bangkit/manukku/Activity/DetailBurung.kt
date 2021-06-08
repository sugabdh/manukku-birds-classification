package com.bangkit.manukku.Activity


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.manukku.DataObject.Bird
import com.bangkit.manukku.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val GambarBurung: ImageView = findViewById(R.id.detail_bird_img)
        val NamaBurung: TextView = findViewById(R.id.detail_bird_name)
        val DeskripsiBurung: TextView = findViewById(R.id.detail_bird_description)
        val NamaIlmiahBurung: TextView = findViewById(R.id.detail_nama_ilmiah)
        val statusBurung: TextView = findViewById(R.id.detail_status_burung)


        val bird = intent.getParcelableExtra<Bird>(EXTRA_USER)
        NamaBurung.text = bird?.birdName
        DeskripsiBurung.text = bird?.birdDescription
        NamaIlmiahBurung.text = bird?.birdIlmiahName
        statusBurung.text = bird?.birdStatus

        Glide.with(this)
            .load(bird?.birdImg)
            .apply(RequestOptions())
            .into(GambarBurung)
    }
}