package com.example.myapplication

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF

abstract class GameSprite : GameObject {

    // The id corresponding to the sprite
    abstract val imageID: Int

    // the bounds of the image to use ()
    // Use this if you only need to use a portion of the image instead of the entire thing
    protected val imgBounds: Rect? = null

    // the location to draw to
    abstract var bounds: RectF

    // Game state that is passed from the main controller
    abstract val gameState: GameState

    override fun draw(canvas: Canvas) {
        val img = gameState.resources.getResource(imageID)
        canvas.drawBitmap(img, imgBounds, bounds, null)
    }

    protected fun translate(x: Float, y: Float) {
        bounds = RectF(bounds.left + x, bounds.top + y, bounds.right + x, bounds.bottom + y)
    }

}