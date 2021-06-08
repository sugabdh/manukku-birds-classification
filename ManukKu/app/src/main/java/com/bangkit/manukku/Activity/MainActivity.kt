package com.bangkit.manukku.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bangkit.manukku.R
import com.bangkit.manukku.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        button()

    }

    private fun button(){
        binding.detectBtn.setOnClickListener(this)
        binding.listBtn.setOnClickListener(this)
        binding.forumBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.detect_btn -> moveToDetectActivity()
            R.id.list_btn -> moveToListActivity()
            R.id.forum_btn -> moveToForumActivity()
        }
    }

    private fun moveToDetectActivity(){
        val intentMoveToDetectActivity = Intent(this@MainActivity, CameraActivity::class.java)
        startActivity(intentMoveToDetectActivity)

    }

    private fun moveToListActivity(){
        val intentMoveToListActivity = Intent(this@MainActivity, ListActivity::class.java)
        startActivity(intentMoveToListActivity)
    }

    private fun moveToForumActivity(){
        val intentMoveToFormActivity = Intent(this@MainActivity, ForumActivity::class.java)
        startActivity(intentMoveToFormActivity)
    }


}