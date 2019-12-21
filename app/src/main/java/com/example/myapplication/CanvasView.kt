package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.view.MotionEventCompat
import com.example.myapplication.gameobjects.Player
import com.example.myapplication.gameobjects.Wall
import kotlin.concurrent.thread

const val FRAMES_PER_SECOND = 30

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var gameState = GameState(context)

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

        for (j in 1..5) {
            for (i in 1..5) {
                gameState.objectPool.add(Wall(gameState, RectF(i * 100.0f, j * 100.0f, (i + 1) * 100.0f, (j + 1) * 100.0f)))
            }
        }
        gameState.objectPool.add(Player(gameState, RectF(800.0f, 1000.0f, 900.0f, 1200.0f)))
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
        for (id in gameState.objectPool.getPoolIds()) {
            if (!gameState.objectPool.isStatic(id)) {
                for (gameObject in gameState.objectPool.getObjects(id)) {
                    gameObject.update(gameState)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.rawX

        val action: Int = event.action

        if (event!!.action == MotionEvent.ACTION_UP) {
            gameState.moveLeft = false
            gameState.moveRight = false
        } else {
            // should be able to move only on the edges of the screen
            // Can refine this later
            gameState.moveLeft = x < 400
            gameState.moveRight = x > gameState.mWidth - 400 //TODO get rid of magic number
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (id in gameState.objectPool.getPoolIds()) {
            for (gameObject in gameState.objectPool.getObjects(id)) {
                gameObject.draw(canvas)
            }
        }
    }
}
