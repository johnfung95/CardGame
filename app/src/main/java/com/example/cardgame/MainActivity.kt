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
import kotlin.random.Random

public var x = 0
public val cardArr = arrayOf(R.drawable.card01, R.drawable.card02, R.drawable.card03, R.drawable.card04, R.drawable.card05, R.drawable.card06, R.drawable.card07, R.drawable.card08, R.drawable.card09, R.drawable.card10, R.drawable.card11, R.drawable.card12, R.drawable.card13, R.drawable.card14, R.drawable.card15, R.drawable.card16, R.drawable.card17, R.drawable.card18, R.drawable.card19, R.drawable.card20, R.drawable.card21, R.drawable.card22, R.drawable.card23, R.drawable.card24, R.drawable.card25, R.drawable.card26, R.drawable.card27, R.drawable.card28, R.drawable.card29, R.drawable.card30, R.drawable.card31, R.drawable.card32, R.drawable.card33, R.drawable.card34, R.drawable.card35, R.drawable.card36, R.drawable.card37, R.drawable.card38, R.drawable.card39, R.drawable.card40, R.drawable.card41, R.drawable.card42, R.drawable.card43, R.drawable.card44, R.drawable.card45, R.drawable.card46, R.drawable.card47, R.drawable.card48, R.drawable.card49, R.drawable.card50, R.drawable.card51, R.drawable.card52)
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
        val robotScore = findViewById<TextView>(R.id.robotscore)
        val playerScore = findViewById<TextView>(R.id.playerscore)

        robotScore.visibility = View.VISIBLE
        playerScore.visibility = View.VISIBLE
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

        ObjectAnimator.ofFloat(drawButton2, "translationY", 500f).apply {
            drawButton2.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(drawButton2, "translationX", 210f).apply {
                duration = 2000
                start()
            }
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(stopButton, "translationY", 500f).apply {
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

        var leftCards = cardArr

        val m = Random.nextInt(cardArr.count())
        val n = Random.nextInt(cardArr.count())

        playCardTv1.setImageResource(cardArr[m])
        playCardTv2.setImageResource(cardArr[n])
    }

    public fun calculatePoints(v: View) {

    }
}