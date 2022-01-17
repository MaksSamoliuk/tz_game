package com.example.tz_game

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tz_game.Game.GameActivity
import com.example.tz_game.Game.StartGameActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defaultsKey = mapOf("web" to true, "game" to false)
        //val myKeytrue = tV_true
        // val myKeyfalse = tV_false

        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
            build()
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(defaultsKey)

        val mTrue = remoteConfig.getBoolean("web")
        val mFalse = remoteConfig.getBoolean("game")

        remoteConfig.fetch().addOnCompleteListener {
            Log.i(TAG, "Fetched values successfully")
            if (mTrue) {
                val intent = Intent(this, WebViewActivity::class.java)
                startActivity(intent)
                Log.i(TAG, "Fetched values true")
            }

            if(!mFalse) {
                val intent = Intent(this, StartGameActivity::class.java)
                startActivity(intent)
                Log.i(TAG, "Fetched values false")
            }
            remoteConfig.fetchAndActivate()
        }
    }
}