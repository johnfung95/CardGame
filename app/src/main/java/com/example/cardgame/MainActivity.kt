package com.example.cardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.os.*

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
            setContentView(R.layout.goodbye)
        }, 3000)
    }
}