package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.gameobjects.Player
import com.example.myapplication.gameobjects.Wall
import kotlin.concurrent.thread
import kotlin.random.Random

const val BLACK = -0x1000000

const val FRAMES_PER_SECOND = 2

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var gameState = GameState(context, this)

    private var paint = Paint()

    init {
        initializeGamestate()

        // spin up a new game logic thread and have that run the game loop
        thread(isDaemon = true) { gameLoop() }
    }

    /**
     * Create all of the gameobjects here
     */
    private fun initializeGamestate() {
        gameState.levelNumber = 1

        paint.color = BLACK
        for (j in 1..5) {
            for (i in 1..5) {
                gameState.objectPool[i.toString() + j.toString()] = Wall(gameState, Rect(i * 100, j * 100, (i + 1) * 100, (j + 1) * 100))
            }
        }
        gameState.objectPool["player"] = Player(gameState, Rect(800, 1000, 900, 1200))
    }

    /**
     * Creates a new thread for the game logic, keeps a consistent UPS
     */
    private fun gameLoop() {
        while(true) {
            val start = SystemClock.elapsedRealtime()
            updateGamestate()
            invalidate()
            val end = SystemClock.elapsedRealtime()

            // sleep only the amount needed
            val timeSinceLastFrame = end - start
            if (timeSinceLastFrame < 1000/FRAMES_PER_SECOND) {
                Thread.sleep(1000 / FRAMES_PER_SECOND - timeSinceLastFrame)
            }
        }
    }

    private fun updateGamestate() {
        for (gameObject in gameState.objectPool.values) {
            gameObject.update(gameState)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {


        invalidate()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Random.nextInt() // just to test if stuff is changing or not
        for (gameObject in gameState.objectPool.values) {
            gameObject.draw(canvas)
        }
    }
}
