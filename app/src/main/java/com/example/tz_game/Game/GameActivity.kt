package com.example.tz_game.Game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tz_game.R
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_start_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var win_line: String
    private var text_view: TextView? = null
    private lateinit var image: ImageView
    private var old_degree = 0
    private var degree = 0
    private val FACTOR = 4.86f
    private var number = arrayOf("32 RED", "15 BLACK", "19 RED", "4 BLACK",
        "21 RED", "2 BLACK", "25 RED", "17 BLACK", "34 RED",
        "6 BLACK", "27 RED", "13 BLACK", "36 RED", "11 BLACK", "30 RED",
        "8 BLACK", "23 RED", "10 BLACK", "5 RED", "24 BLACK", "16 RED", "33 BLACK",
        "1 RED", "20 BLACK", "14 RED", "31 BLACK", "9 RED", "22 BLACK", "18 RED",
        "29 BLACK", "7 RED", "28 BLACK", "12 RED", "35 BLACK", "3 RED", "26 BLACK", "0 GREEN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        win_line = intent.extras?.getString("color").toString()



        text_view = tvResult
        image = ryletka
        button = btn_start



        button.setOnClickListener {
            old_degree = degree % 360

            degree = Random.nextInt(3600) + 720

            val rotate = RotateAnimation(old_degree.toFloat(),
                degree.toFloat(), RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 3600
            rotate.fillAfter = true
            rotate.interpolator = DecelerateInterpolator()
            rotate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    text_view?.text = ""
                }

                override fun onAnimationEnd(animation: Animation?) {
                    text_view?.setText(getResult(360 - (degree % 360)))
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
            image.startAnimation(rotate)
        }
    }


    private fun getResult(degree: Int): String {
        var text: String = ""
        var factor_x = 1
        var factor_y = 3
        for (i in 0..37) {
            if (degree >= (FACTOR * factor_x) && degree < (FACTOR * factor_y)) {
                text = number[i]
            }
            factor_x += 2
            factor_y += 2
        }
        if (degree >= (FACTOR * 73) && (degree < 360) || (degree >= 0 && degree < FACTOR * 1)) {
            text = number[number.lastIndex - 1]
        }

        if (text.contains(win_line)) {
            text += "\nYOU WON"
        } else {
            text += "\nYOU LOST"
        }


        return text
    }


}