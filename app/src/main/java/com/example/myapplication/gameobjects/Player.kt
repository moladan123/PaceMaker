package com.example.myapplication.gameobjects

import android.graphics.RectF
import com.example.myapplication.GameObject
import com.example.myapplication.GameState
import com.example.myapplication.R

const val PLAYER_SPEED = 2.5f

class Player(override val gameState: GameState, override var bounds: RectF) : GameObject() {

    override val isStatic = false

    override val imageID = R.drawable.person

    override val isSolid = true

    // downward acceleration and speed (gravity)
    private val ay = 0.05f
    private var vy  = 0f

    // movement in horizontal direction
    private var vx = 2.5f

    override fun update(gameState: GameState) {

        // gravity
        vy += ay
        translate(0f, vy)

        // check if there is a solid object below
        for (id in gameState.objectPool.getPoolIds()) {
            for (gameObject in gameState.objectPool.getObjects(id)) {
                if (gameObject.imageID != imageID && gameObject.isSolid && bounds.intersects(gameObject.bounds.left, gameObject.bounds.top, gameObject.bounds.right, gameObject.bounds.bottom)) {
                    vy = 0f
                    translate(0f, gameObject.bounds.top - this.bounds.bottom)
                    gameObject.onCollide(this)
                }
            }
        }

        // moves left and right (turns around whenever it hits a solid object)
        translate(vx, 0f)
        for (id in gameState.objectPool.getPoolIds()) {
            for (gameObject in gameState.objectPool.getObjects(id)) {
                if (gameObject.imageID != imageID && gameObject.isSolid && bounds.intersects(gameObject.bounds.left, gameObject.bounds.top, gameObject.bounds.right, gameObject.bounds.bottom)) {
                    vx *= -1f
                    translate(vx, 0f)
                    gameObject.onCollide(this)
                }
            }
        }

    }
}