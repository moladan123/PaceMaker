package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.myapplication.gameobjects.Wall
import kotlin.concurrent.thread
import kotlin.random.Random

/**
 * TODO: document your custom view class.
 */

const val BLACK = -0x1000000

const val FRAMES_PER_SECOND = 2

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var objectPool = HashMap<String, GameObject>()

    private var paint = Paint()

    init {
        initializeGamestate(objectPool)

        // spin up a new game logic thread and have that run the game loop
        thread(isDaemon = true) { gameLoop() }

    }

    private fun gameLoop() {
        while(true) {
            val start = SystemClock.elapsedRealtime()
            updateGamestate(objectPool)
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
     * Create all of the gameobjects here
     *
     */
    private fun initializeGamestate(objectPool: HashMap<String, GameObject>) {
        paint.color = BLACK
        for (i in 1..5) {
            objectPool[i.toString()] = Wall(paint, Rect(i*100, i*100, (i+1)*100, (i+1)*100))
        }


    }

    private fun updateGamestate(objectPool: Map<String, GameObject>) {
        for (gameObject in objectPool.values) {
            gameObject.update(objectPool)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {


        invalidate()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Random.nextInt() // just to test if stuff is changing or not
        for (gameObject in objectPool.values) {
            gameObject.draw(canvas)
        }
    }
}
