package com.example.myapplication.gameobjects

import android.content.res.Resources
import android.graphics.RectF
import com.example.myapplication.GameSprite
import com.example.myapplication.GameState
import com.example.myapplication.R

const val PLAYER_SPEED = 2.5f

class Player(override val gameState: GameState, override var bounds: RectF) : GameSprite() {
    override val imageID = R.drawable.person

    override fun update(gameState: GameState) {

        //TODO add logic to check the player is not moving into something else

        // move left or right
        if (gameState.moveLeft) {
            translate( maxOf(-PLAYER_SPEED, -bounds.left), .0f)
        } else if (gameState.moveRight) {
            translate( minOf(PLAYER_SPEED, gameState.mWidth - bounds.right), .0f)
        }

        // TODO gravity

    }
}