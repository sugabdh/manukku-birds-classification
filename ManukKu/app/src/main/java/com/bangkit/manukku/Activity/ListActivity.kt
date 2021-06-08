package com.bangkit.manukku.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.manukku.Adapter.AdapterBird
import com.bangkit.manukku.DataObject.Bird
import com.bangkit.manukku.R

class ListActivity : AppCompatActivity() {
    private lateinit var recyclerV: RecyclerView
    private val list_burung = ArrayList<Bird>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerV = findViewById(R.id.rv_bird)
        recyclerV.setHasFixedSize(true)


        showRecylcerList()
        fromResource()
    }

    @SuppressLint("Recycle")
    private fun fromResource(): ArrayList<Bird> {
        val resImg: TypedArray = resources.obtainTypedArray(R.array.gambar_burung)
        val resNama: Array<String> = resources.getStringArray(R.array.nama_burung)
        val resNamaIlmiah: Array<String> = resources.getStringArray(R.array.nama_ilmiah_burung)
        val resStatus: Array<String> = resources.getStringArray(R.array.status_burung)
        val resDeskripsi: Array<String> = resources.getStringArray(R.array.deskripsi_burung)



        for (i in resNama.indices) {
            val itemUser = Bird(
                resImg.getResourceId(i, -1),
                resNama[i],
                resNamaIlmiah[i],
                resStatus[i],
                resDeskripsi[i]
            )
            list_burung.add(itemUser)
        }
        return list_burung
    }

    private fun showRecylcerList() {
        recyclerV.layoutManager = LinearLayoutManager(this)
        val birdAdapter = AdapterBird(fromResource())
        recyclerV.adapter = birdAdapter

        birdAdapter.setOnItemClickCallback(object : AdapterBird.OnItemClickCallback {
            override fun onItemClicked(data: Bird) {
                val intent = Intent(this@ListActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, data)
                startActivity(intent)
            }
        })
    }
}