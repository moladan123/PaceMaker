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
    private val ax = 0.05f
    private var vx  = 0f

    override fun update(gameState: GameState) {

        // gravity
        vx += ax
        translate(0f, vx)

        // check if there is a solid object below
        for (id in gameState.objectPool.getPoolIds()) {
            for (gameObject in gameState.objectPool.getObjects(id)) {
                if (gameObject.imageID != imageID && gameObject.isSolid && bounds.intersects(gameObject.bounds.left, gameObject.bounds.top, gameObject.bounds.right, gameObject.bounds.bottom)) {
                    vx = 0f
                    translate(0f, gameObject.bounds.top - this.bounds.bottom)
                }
            }
        }

        //TODO add move left and right logic

    }
}