package com.example.tz_game.Game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.tz_game.R
import kotlinx.android.synthetic.main.activity_start_game.*

class StartGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        btn_black.setOnClickListener {
            OpenGame("BLACK")
        }
        btn_red.setOnClickListener {
            OpenGame("RED")
        }
        btn_green.setOnClickListener {
            OpenGame("GREEN")
        }
    }

    private fun OpenGame(color: String) {

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("color", color)
        startActivity(intent)
    }
}