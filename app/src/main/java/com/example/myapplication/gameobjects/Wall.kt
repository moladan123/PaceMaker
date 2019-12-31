package com.example.myapplication.gameobjects

import android.graphics.RectF
import com.example.myapplication.GameObject
import com.example.myapplication.GameState
import com.example.myapplication.R

class Wall(override val gameState: GameState, override var bounds: RectF) : GameObject() {
    override val isSolid = true

    override val isStatic = true

    override fun update(gameState: GameState) {}

    override val imageID: Int = R.drawable.wall



}