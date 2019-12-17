package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.myapplication.gameobjects.Wall
import kotlin.random.Random

/**
 * TODO: document your custom view class.
 */

const val BLACK = -0x1000000

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var objectPool = ArrayList<GameObject>();

    private var paint = Paint()

    init {
        paint.color = BLACK

        // initialize a few squares for testing
        for (i in 1..5) {
            val r = Rect(i*100, i*100, i*100 + 100, i*100 + 100)
            objectPool.add(Wall(paint, r))
        }
    }

    override fun onDraw(canvas: Canvas) {

        for (i in 1..20) {
            super.onDraw(canvas)

            paint.color = Random.nextInt()
            for (gameObject in objectPool) {
                gameObject.draw(canvas)
            }
        }
    }
}
