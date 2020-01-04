package com.example.myapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color.BLACK
import android.graphics.Paint
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

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    private val textPaint = Paint()

    init {
        textPaint.color = BLACK
        textPaint.textSize = 36f

        gameState.levelNumber = 1

        // spin up a new game logic thread and have that run the game loop
        thread { gameLoop() }
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

    /**
     * Updates all the gameObjects in the pool
     */
    private fun updateGamestate() = synchronized(this) {

        if (gameState.levelNumber > 1) {
            println("hello there")
        }
        for (id in gameState.objectPool.getPoolIds()) {
            if (!gameState.objectPool.isStatic(id)) {
                for (gameObject in gameState.objectPool.getObjects(id)) {
                    gameObject.update(gameState)
                }
            }
        }
        if (gameState.levelNumber > 1) {
            println("general kenobi")
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.rawX
        val y = event!!.rawY

        synchronized(this) {
            gameState.mouseX = x * gameState.levelWidth / screenWidth
            gameState.mouseY = y * gameState.levelHeight / screenHeight
            gameState.mouseDown = (event.action != MotionEvent.ACTION_UP)
        }

        println(String.format("pointer coords = (%2f, %2f)", gameState.mouseX, gameState.mouseY))

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        synchronized(this) {
            // draw all gameobjects
            for (id in gameState.objectPool.getPoolIds()) {
                val img = gameState.resources.getResource(id)
                if (id == R.drawable.static_wall) {
                    val walls
                }

                for (gameObject in gameState.objectPool.getObjects(id)) {
                    canvas.drawBitmap(img, gameObject.imgBounds, gameObject.getScreenBounds(), null)
                }
            }

            // display text overlay
            canvas.drawText(String.format("Using %d/%d paint", gameState.paintUsed, gameState.maxPaint), 10f, 25f, textPaint)
        }
    }
}
