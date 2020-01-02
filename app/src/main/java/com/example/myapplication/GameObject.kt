package com.example.myapplication

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF

abstract class GameObject{

    // The id corresponding to the sprite
    abstract val imageID: Int

    // the bounds of the image to use ()
    // Use this if you only need to use a portion of the image instead of the entire thing
    open val imgBounds: Rect? = null

    // the location to draw to
    abstract var bounds: RectF

    // Game state that is passed from the main controller
    abstract val gameState: GameState

    // objects that are static will not update
    // This is an optimization so walls do not update every single tick
    abstract val isStatic: Boolean

    // whether the object has collision or not
    abstract val isSolid: Boolean

    /**
     * Move the object
     */
    protected fun translate(x: Float, y: Float) {
        bounds = RectF(bounds.left + x, bounds.top + y, bounds.right + x, bounds.bottom + y)
    }

    /**
     * advance the state of the current object by one frame
     * All of the game logic of this object rests here
     */
    open fun update(gameState: GameState) {}

    /**
     * Gets the actual coordinates of the screen (this is what is used to determine collision and is what is drawn to the screen)
     */
    open fun getScreenBounds(): RectF {
        return RectF((bounds.left - gameState.cameraBounds.left) * (gameState.levelWidth/gameState.cameraBounds.right),
                (bounds.top - gameState.cameraBounds.top) * (gameState.levelHeight / gameState.cameraBounds.bottom),
                (bounds.right - gameState.cameraBounds.left) * (gameState.levelWidth / gameState.cameraBounds.right),
                (bounds.bottom - gameState.cameraBounds.top) * (gameState.levelHeight / gameState.cameraBounds.bottom)
                )
    }

    /**
     * Called whenever an object collides with it
     */
    open fun onCollide(other: GameObject) {}

}