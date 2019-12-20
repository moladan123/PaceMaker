package com.example.myapplication

import android.graphics.Canvas
import android.graphics.Rect

abstract class GameSprite : GameObject {

    // The id corresponding to the sprite
    abstract val imageID: Int

    // the bounds of the image to use ()
    protected val imgBounds: Rect? = null

    // the location to draw to
    abstract var bounds: Rect

    // Game state that is passed from the main controller
    abstract val gameState: GameState

    override fun draw(canvas: Canvas) {
        val img = gameState.resources.getResource(imageID)
        canvas.drawBitmap(img, imgBounds, bounds, null)
    }

}