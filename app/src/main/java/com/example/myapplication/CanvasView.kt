package com.example.myapplication

import android.content.Context
import android.content.res.Resources
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


    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        initializeGamestate()

        // spin up a new game logic thread and have that run the game loop
        thread(isDaemon = true) { gameLoop() }
    }

    /**
     * Create all of the gameobjects here
     */
    private fun initializeGamestate() {
        gameState.nextLevel()
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

        if (event.action == MotionEvent.ACTION_UP) {
            gameState.moveLeft = false
            gameState.moveRight = false
        } else {
            // should be able to move only on the edges of the screen
            // Can refine this later
            gameState.moveLeft = x < screenWidth / 2
            gameState.moveRight = x >= screenWidth/ 2
        }

        return true
    }

    private fun fitToScreen(bounds: RectF): RectF {
        return RectF(bounds.left * gameState.levelWidth / screenWidth,
                     bounds.top * gameState.levelHeight / screenHeight,
                     bounds.right * gameState.levelWidth / screenWidth,
                     bounds.bottom * gameState.levelWidth / screenWidth
                )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (id in gameState.objectPool.getPoolIds()) {
            val img = gameState.resources.getResource(id)
            for (gameObject in gameState.objectPool.getObjects(id)) {
                canvas.drawBitmap(img, gameObject.imgBounds, gameObject.getScreenBounds(), null)
            }
        }
    }
}
