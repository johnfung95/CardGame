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

var x: Int = 0
var round: Int = 0
var cardArr = arrayListOf(R.drawable.card01, R.drawable.card02, R.drawable.card03, R.drawable.card04, R.drawable.card05, R.drawable.card06, R.drawable.card07, R.drawable.card08, R.drawable.card09, R.drawable.card10, R.drawable.card11, R.drawable.card12, R.drawable.card13, R.drawable.card14, R.drawable.card15, R.drawable.card16, R.drawable.card17, R.drawable.card18, R.drawable.card19, R.drawable.card20, R.drawable.card21, R.drawable.card22, R.drawable.card23, R.drawable.card24, R.drawable.card25, R.drawable.card26, R.drawable.card27, R.drawable.card28, R.drawable.card29, R.drawable.card30, R.drawable.card31, R.drawable.card32, R.drawable.card33, R.drawable.card34, R.drawable.card35, R.drawable.card36, R.drawable.card37, R.drawable.card38, R.drawable.card39, R.drawable.card40, R.drawable.card41, R.drawable.card42, R.drawable.card43, R.drawable.card44, R.drawable.card45, R.drawable.card46, R.drawable.card47, R.drawable.card48, R.drawable.card49, R.drawable.card50, R.drawable.card51, R.drawable.card52)
var pointsArr = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52)
val handler = Handler(Looper.getMainLooper())
var totalPlayerScore = 0
var totalRobotScore = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Debug", "Start app")
        super.onCreate(savedInstanceState)
        Log.d("Debug", "Start displaying instructions")
        setContentView(R.layout.instructions)
    }

    fun createBot(v: View) {
        Log.d("Debug", "fun createBot Start")
        /*TODO: do a little animation
        - to make the ... flash for a couple of times*/
        setContentView(R.layout.createbot)
        handler.postDelayed({
            Log.d("Debug", "creating some wait for while creating bot")
            setContentView(R.layout.playgame)
        }, 3000)
        Log.d("Debug", "fun createBot End")
    }

    fun startDrawCard(v:View) {
        Log.d("Debug", "fun startDrawCard Start")
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

        playDrawCard(v)
        Log.d("Debug", "fun startDrawCard End")
    }

    fun playDrawCard(v:View) {
        Log.d("Debug", "fun playDrawCard Start")
        val playCardTv1 = findViewById<ImageView>(R.id.playercard1)
        val playCardTv2 = findViewById<ImageView>(R.id.playercard2)
        val robotCardTv1 = findViewById<ImageView>(R.id.robotcard1)
        val robotCardTv2 = findViewById<ImageView>(R.id.robotcard2)
        val playerScore = findViewById<TextView>(R.id.playerscore)
        val robotScore = findViewById<TextView>(R.id.robotscore)
        val centerText = findViewById<TextView>(R.id.centertext)
        var currentPlayerScore = 0
        var currentRobotScore = 0

        round += 1
        Log.d("Debug", "round: " + round)

        // TODO: consider making an interface for robot and player
        var maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(playCardTv1,  maxCount, true)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(playCardTv2,  maxCount, true)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(robotCardTv1, maxCount, false)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(robotCardTv2, maxCount, false)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)

        if (maxCount == 0) {
            Log.d("Debug", "no more cards and go to calculate scores")
            calculatePointsAtEnd(v)
        } else {
            centerText.text = "Round: " + round + "\nCards Left: " + maxCount
            playerScore.text = "Player Scores: " + totalPlayerScore
            robotScore.text = "Robot Scores: " + totalRobotScore
        }
        Log.d("Debug", "fun calculatePoints Start")
    }

    private fun playerGetCardsFromArray(tv:ImageView,  maxCount:Int, isPlayer: Boolean = false) {
        Log.d("Debug", "fun playerGetCardsFromArray Start")
        var n = Random.nextInt(maxCount)
        Log.d("Debug","n: " + n)
        Log.d("Debug","cardArr[n]: " + cardArr[n])
        if (isPlayer) {
            tv.setImageResource(cardArr[n])
            Log.d("Debug","initial cardArr.count(): " + cardArr.count())
            Log.d("Debug","initial pointsArr.count(): " + pointsArr.count())
            cardArr.removeAt(n)
            pointsArr.removeAt(n)
            Log.d("Debug","after remove cardArr.count(): " + cardArr.count())
            Log.d("Debug","after remove pointsArr.count(): " + pointsArr.count())
        } else {
            Log.d("Debug","initial cardArr.count(): " + cardArr.count())
            Log.d("Debug","initial pointsArr.count(): " + pointsArr.count())
            cardArr.removeAt(n)
            pointsArr.removeAt(n)
            Log.d("Debug","after remove cardArr.count(): " + cardArr.count())
            Log.d("Debug","after remove pointsArr.count(): " + pointsArr.count())
        }
        Log.d("Debug", "fun playDrawCard End")
    }

    fun calculatePointsAtEnd(v: View) {
        Log.d("Debug", "fun calculatePoints Start")
        Log.d("Debug", "Display calculate score layout")
        setContentView(R.layout.calscore)

        handler.postDelayed({
            Log.d("Debug", "Display goodbye layout")
            setContentView(R.layout.goodbye)
        }, 3000)
        Log.d("Debug", "fun calculatePoints End")
    }
}