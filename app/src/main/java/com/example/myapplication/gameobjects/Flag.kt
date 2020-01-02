package com.example.myapplication.gameobjects

import android.graphics.RectF
import com.example.myapplication.GameObject
import com.example.myapplication.GameState
import com.example.myapplication.R

class Flag(override val gameState: GameState, override var bounds: RectF): GameObject() {
    override val isStatic = true
    override val isSolid = true
    override val imageID = R.drawable.flag

    override fun onCollide(other: GameObject) {
        if (other.imageID == R.drawable.person) {
            gameState.levelNumber = gameState.levelNumber + 1
        }
    }
}