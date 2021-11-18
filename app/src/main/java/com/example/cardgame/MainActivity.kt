package com.example.cardgame

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.os.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

public var x = 0
public val cardArr = arrayOf(R.drawable.card01, R.drawable.card02)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.instructions)
    }

    public fun createBot(v: View) {
        /*TODO: do a little animation
        - to make the ... flash for a couple of times*/

        setContentView(R.layout.createbot)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            Log.d("Debug", "creating some wait for while creating bot")
            setContentView(R.layout.playgame)
        }, 3000)
    }

    public fun startDrawCard(v:View) {
        val playcard1 = findViewById<ImageView>(R.id.playercard1)
        val playcard2 = findViewById<ImageView>(R.id.playercard2)

        val robotcard1 = findViewById<ImageView>(R.id.robotcard1)
        val robotcard2 = findViewById<ImageView>(R.id.robotcard2)

        val centerText = findViewById<TextView>(R.id.centertext)

        val drawButton1 = findViewById<Button>(R.id.drawbutton1)
        val drawButton2 = findViewById<Button>(R.id.drawbutton2)
        val stopButton = findViewById<Button>(R.id.stopbutton)

        drawButton1.visibility = View.INVISIBLE

        ObjectAnimator.ofFloat(centerText, "translationY", -100f).apply {
            x += 1
            centerText.text  = "Round : " + x +"\nCards Left: " + cardArr.count()
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(playcard1, "translationY", 800f).apply {
            playcard1.visibility = View.VISIBLE
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(playcard2, "translationY", 800f).apply {
            playcard2.visibility = View.VISIBLE
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(robotcard1, "translationY", -50f).apply {
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(robotcard2, "translationY", -50f).apply {
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(drawButton2, "translationY", 400f).apply {
            drawButton2.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(drawButton2, "translationX", 200f).apply {
                duration = 2000
                start()
            }
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(stopButton, "translationY", 400f).apply {
            stopButton.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(stopButton, "translationX", -180f).apply {
                duration = 2000
                start()
            }
            duration = 2000
            start()
        }
    }

    public fun playDrawCard(v:View) {
        val playCardTv1 = findViewById<ImageView>(R.id.playercard1)
        val playCardTv2 = findViewById<ImageView>(R.id.playercard2)


        playCardTv1.setImageResource(R.drawable.card01)
        playCardTv2.setImageResource(R.drawable.card02)
    }

    public fun calculatePoints(v: View) {

    }
}