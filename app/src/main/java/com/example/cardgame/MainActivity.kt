package com.example.cardgame

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.os.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.emitters.StreamEmitter
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import kotlin.random.Random

var x: Int = 0
var round: Int = 0
var cardArr = arrayListOf(R.drawable.card01, R.drawable.card02, R.drawable.card03, R.drawable.card04, R.drawable.card05, R.drawable.card06, R.drawable.card07, R.drawable.card08, R.drawable.card09, R.drawable.card10, R.drawable.card11, R.drawable.card12, R.drawable.card13, R.drawable.card14, R.drawable.card15, R.drawable.card16, R.drawable.card17, R.drawable.card18, R.drawable.card19, R.drawable.card20, R.drawable.card21, R.drawable.card22, R.drawable.card23, R.drawable.card24, R.drawable.card25, R.drawable.card26, R.drawable.card27, R.drawable.card28, R.drawable.card29, R.drawable.card30, R.drawable.card31, R.drawable.card32, R.drawable.card33, R.drawable.card34, R.drawable.card35, R.drawable.card36, R.drawable.card37, R.drawable.card38, R.drawable.card39, R.drawable.card40, R.drawable.card41, R.drawable.card42, R.drawable.card43, R.drawable.card44, R.drawable.card45, R.drawable.card46, R.drawable.card47, R.drawable.card48, R.drawable.card49, R.drawable.card50, R.drawable.card51, R.drawable.card52)
var endImgArr = arrayListOf(R.drawable.loser, R.drawable.draw, R.drawable.smirk)
var pointsArr = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52)
val handler = Handler(Looper.getMainLooper())
var currentPlayerScore1 = 0
var currentPlayerScore2 = 0
var currentRobotScore1 = 0
var currentRobotScore2 = 0
var totalPlayerScore = 0
var totalRobotScore = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Debug", "onCreate() Start")
        super.onCreate(savedInstanceState)
        Log.d("Debug", "Start displaying instructions")
        setContentView(R.layout.instructions)
    }

    fun createBot(v: View) {
        Log.d("Debug", "fun createBot() Start")
        /*TODO: do a little animation
        - to make the ... flash for a couple of times*/
        setContentView(R.layout.createbot)
        handler.postDelayed({
            Log.d("Debug", "creating some wait for while creating bot")
            setContentView(R.layout.playgame)
        }, 3000)
        Log.d("Debug", "fun createBot() End")
    }

    fun playAgain(v:View) {
        //TODO: refactor to a class and have it reset every time
        x = 0
        round = 0
        cardArr = arrayListOf(R.drawable.card01, R.drawable.card02, R.drawable.card03, R.drawable.card04, R.drawable.card05, R.drawable.card06, R.drawable.card07, R.drawable.card08, R.drawable.card09, R.drawable.card10, R.drawable.card11, R.drawable.card12, R.drawable.card13, R.drawable.card14, R.drawable.card15, R.drawable.card16, R.drawable.card17, R.drawable.card18, R.drawable.card19, R.drawable.card20, R.drawable.card21, R.drawable.card22, R.drawable.card23, R.drawable.card24, R.drawable.card25, R.drawable.card26, R.drawable.card27, R.drawable.card28, R.drawable.card29, R.drawable.card30, R.drawable.card31, R.drawable.card32, R.drawable.card33, R.drawable.card34, R.drawable.card35, R.drawable.card36, R.drawable.card37, R.drawable.card38, R.drawable.card39, R.drawable.card40, R.drawable.card41, R.drawable.card42, R.drawable.card43, R.drawable.card44, R.drawable.card45, R.drawable.card46, R.drawable.card47, R.drawable.card48, R.drawable.card49, R.drawable.card50, R.drawable.card51, R.drawable.card52)
        pointsArr = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52)
        currentPlayerScore1 = 0
        currentPlayerScore2 = 0
        currentRobotScore1 = 0
        currentRobotScore2 = 0
        totalPlayerScore = 0
        totalRobotScore = 0
        setContentView(R.layout.instructions)
    }

    fun startDrawCard(v:View) {
        Log.d("Debug", "fun startDrawCard() Start")
        val playerCard1 = findViewById<ImageView>(R.id.playercard1)
        val playerCard2 = findViewById<ImageView>(R.id.playercard2)
        val robotCard1 = findViewById<ImageView>(R.id.robotcard1)
        val robotCard2 = findViewById<ImageView>(R.id.robotcard2)
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

        ObjectAnimator.ofFloat(playerCard1, "translationY", 800f).apply {
            playerCard1.visibility = View.VISIBLE
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(playerCard2, "translationY", 800f).apply {
            playerCard2.visibility = View.VISIBLE
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(robotCard1, "translationY", -50f).apply {
            duration = 2000
            start()
        }

        ObjectAnimator.ofFloat(robotCard2, "translationY", -50f).apply {
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
        Log.d("Debug", "fun startDrawCard() End")
    }

    fun playDrawCard(v:View) {
        Log.d("Debug", "fun playDrawCard() Start")
        val playCardTv1 = findViewById<ImageView>(R.id.playercard1)
        val playCardTv2 = findViewById<ImageView>(R.id.playercard2)
        val robotCardTv1 = findViewById<ImageView>(R.id.robotcard1)
        val robotCardTv2 = findViewById<ImageView>(R.id.robotcard2)
        val playerScore = findViewById<TextView>(R.id.playerscore)
        val robotScore = findViewById<TextView>(R.id.robotscore)
        val centerText = findViewById<TextView>(R.id.centertext)
        val drawButton1 = findViewById<Button>(R.id.drawbutton1)
        val drawButton2 = findViewById<Button>(R.id.drawbutton2)
        val stopButton = findViewById<Button>(R.id.stopbutton)
        var thisRoundPlayerScore = 0
        var thisRoundRobotScore = 0

        round += 1
        Log.d("Debug", "round: " + round)

        // TODO: consider making an interface for robot and player
        var maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(playCardTv1,  maxCount, true,1)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(playCardTv2,  maxCount, true, 2)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(robotCardTv1, maxCount, false, 1)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)
        playerGetCardsFromArray(robotCardTv2, maxCount, false, 2)
        maxCount = cardArr.count()
        Log.d("Debug", "maxCount: " + maxCount)

        Log.d("Debug", "Player round score start")
        thisRoundPlayerScore = currentScoreCal(currentPlayerScore1, currentPlayerScore2)
        Log.d("Debug", "thisRoundPlayerScore: " + thisRoundPlayerScore)
        Log.d("Debug", "Robot round score start")
        thisRoundRobotScore = currentScoreCal(currentRobotScore1, currentRobotScore2)
        Log.d("Debug", "thisRoundRobotScore: " + thisRoundRobotScore)
        totalPlayerScore += thisRoundPlayerScore
        Log.d("Debug", "totalPlayerScore: " + totalPlayerScore)
        totalRobotScore += thisRoundRobotScore
        Log.d("Debug", "totalRobotScore: " + totalRobotScore)

        centerText.text = "Round: " + round + "\nCards Left: " + maxCount
        playerScore.text = "Current Player Round Scores: " + thisRoundPlayerScore + "\nPlayer Total Scores: " + totalPlayerScore
        robotScore.text = "Current Robot Round Scores: " + thisRoundRobotScore + "\nRobot Total Scores: " + totalRobotScore
        if (maxCount == 0) {
            Log.d("Debug", "no more cards and go to calculate scores")
            drawButton1.isClickable = false
            drawButton2.isClickable = false
            stopButton.isClickable = false
            handler.postDelayed({calculatePointsAtEnd(v)}, 2000)

        }
        Log.d("Debug", "fun playDrawCard() End")
    }

    private fun currentScoreCal(card1Score:Int, card2Score:Int): Int {
        Log.d("Debug", "fun currentScoreCal() Start")
        Log.d("Debug", "card1Score: " + card1Score)
        Log.d("Debug", "card2Score: " + card2Score)
        val pairScore = card1Score * card2Score
        Log.d("Debug", "pairScore: " + pairScore)

        if (pairScore == 1) {
            Log.d("Debug", "fun currentScoreCal() End")
            Log.d("Debug", "A pair, return: " + 100)
            return 100
        } else if (pairScore == 121 || pairScore == 144 || pairScore == 169) {
            Log.d("Debug", "fun currentScoreCal() End")
            Log.d("Debug", "K/Q/J pair, return: " + 50)
            return 50
        } else if (pairScore == 100) {
            Log.d("Debug", "fun currentScoreCal() End")
            Log.d("Debug", "10 pair, return: " + 20)
            return 20
        } else if (pairScore == 81 || pairScore == 64 || pairScore == 49 || pairScore == 36 || pairScore == 25 || pairScore == 16 || pairScore == 9 || pairScore == 4) {
            if (card1Score == card2Score) {
                Log.d("Debug", "fun currentScoreCal() End")
                Log.d("Debug", "Other pair, return: " + 10)
                return 10
            }
        }
        if (card1Score == 1 || card2Score == 1) {
            Log.d("Debug", "fun currentScoreCal() End")
            Log.d("Debug", "Single A, return: " + 5)
            return 5
        }
        Log.d("Debug", "fun currentScoreCal() End")
        Log.d("Debug", "No A and no pair, return: " + -2)
        return -2
    }

    private fun playerGetCardsFromArray(tv:ImageView,  maxCount:Int, isPlayer: Boolean = false, cardNum: Int) {
        Log.d("Debug", "fun playerGetCardsFromArray() Start")
        var n = Random.nextInt(maxCount)
        Log.d("Debug","n: " + n)
        Log.d("Debug","cardArr[n]: " + cardArr[n])
        if (isPlayer) {
            tv.setImageResource(cardArr[n])
        }
        Log.d("Debug","initial cardArr.count(): " + cardArr.count())
        Log.d("Debug","initial pointsArr.count(): " + pointsArr.count())
        cardArr.removeAt(n)
        getCardPoints(pointsArr[n], cardNum, isPlayer)
        pointsArr.removeAt(n)
        Log.d("Debug","after remove cardArr.count(): " + cardArr.count())
        Log.d("Debug","after remove pointsArr.count(): " + pointsArr.count())
        Log.d("Debug", "fun playerGetCardsFromArray() End")
    }

    fun calculatePointsAtEnd(v:View) {
        Log.d("Debug", "fun calculatePointsAtEnd() Start")
        Log.d("Debug", "Display calculate score layout")
        setContentView(R.layout.calscore)

        val robotTotal = findViewById<TextView>(R.id.robotTotal)
        val playerTotal = findViewById<TextView>(R.id.playerTotal)

        val robotValAnimator = ValueAnimator.ofInt(totalRobotScore)
        robotValAnimator.setDuration(500)
        robotValAnimator.addUpdateListener { robotTotal.text = "Robot Total Score:\n" + robotValAnimator.getAnimatedValue().toString() }
        robotValAnimator.start()
        val playerValAnimator = ValueAnimator.ofInt(totalPlayerScore)
        playerValAnimator.setDuration(500)
        playerValAnimator.addUpdateListener { playerTotal.text = "Player Total Score:\n" + playerValAnimator.getAnimatedValue().toString() }
        playerValAnimator.start()

        handler.postDelayed({
            Log.d("Debug", "Display goodbye layout")
            setContentView(R.layout.endingpage)
            val endButton = findViewById<Button>(R.id.endbutton)
            val playAgainButton = findViewById<Button>(R.id.playagainbutton)
            val endingPageTv = findViewById<TextView>(R.id.endingText)
            val endImgTv = findViewById<ImageView>(R.id.endImg)
            val confettiView = findViewById<KonfettiView>(R.id.konfettiView);

            endButton.visibility = View.VISIBLE
            playAgainButton.visibility = View.VISIBLE
            when {
                totalPlayerScore == totalRobotScore -> {
                    Log.d("Debug", "Draw")
                    endImgTv.visibility = View.VISIBLE
                    endImgTv.setImageResource(endImgArr[1])
                    endingPageTv.text = "Not bad\nDRAW!"
                }
                totalPlayerScore > totalRobotScore -> {
                    Log.d("Debug", "Win, showing confetti")
                    endImgTv.visibility = View.INVISIBLE
                    confettiView.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.Square, Shape.Circle)
                        .addSizes(Size(12, 5f))
                        .setPosition(-50f, confettiView.getWidth() + 50f, -50f, -50f)
                        .streamFor(300,emittingTime = StreamEmitter.INDEFINITE)
                    endImgTv.visibility = View.VISIBLE
                    endImgTv.setImageResource(endImgArr[2])
                    endingPageTv.text = "Congradulations\nYou WIN!\nYou got lucky"
                }
                else -> {
                    Log.d("Debug", "Haha, You Loser!")
                    endImgTv.visibility = View.VISIBLE
                    endImgTv.setImageResource(endImgArr[0])
                    endingPageTv.text = "Unfortunately,\nYou LOSE!\nTry Again~"
                }
            }
        }, 5000)
        Log.d("Debug", "fun calculatePointsAtEnd() End")
    }

    fun getCardPoints(points: Int, num: Int, isPlayer: Boolean = false) {
        Log.d("Debug", "fun getCardPoints() Start")
        var beforeSubtract = 0

        beforeSubtract = points
        while(beforeSubtract > 0) {
            Log.d("Debug", "beforeSubtract " + beforeSubtract)
            if (beforeSubtract - 13 <= 0) {
                Log.d("Debug", "beforeSubtract: " + beforeSubtract)
                if (num == 1 && isPlayer) {
                    Log.d("Debug", "1st Player card points")
                    currentPlayerScore1 = beforeSubtract
                    Log.d("Debug", "currentPlayerScore1: " + currentPlayerScore1)
                } else if (num == 2 && isPlayer) {
                    Log.d("Debug", "2nd Player card points")
                    currentPlayerScore2 = beforeSubtract
                    Log.d("Debug", "currentPlayerScore2: " + currentPlayerScore2)
                } else if (num == 1 && !isPlayer) {
                    Log.d("Debug", "1st Robot card points")
                    currentRobotScore1 = beforeSubtract
                    Log.d("Debug", "currentRobotScore1: " + currentRobotScore1)
                } else if (num == 2 && !isPlayer) {
                    Log.d("Debug", "2nd Robot card points")
                    currentRobotScore2 = beforeSubtract
                    Log.d("Debug", "currentRobotScore2: " + currentRobotScore2)
                }
                break
            }
            beforeSubtract -= 13
        }
        Log.d("Debug", "fun getCardPoints() End")
    }

    fun quitGame(v:View) {
        Log.d("Debug", "fun quitGame() Start" )
        setContentView(R.layout.goodbye)
        handler.postDelayed({
            finish()
        }, 3000)

        Log.d("Debug", "fun quitGame() End" )
    }
}