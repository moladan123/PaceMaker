package com.example.myapplication.gameobjects

import android.graphics.RectF
import com.example.myapplication.GameObject
import com.example.myapplication.GameState
import com.example.myapplication.R

class StaticWall(override val gameState: GameState, initialBounds: RectF) : GameObject() {

    override val imageID = R.drawable.static_wall
    override var bounds: RectF
        get() = RectF()
        set(value) {}
    override val isStatic = true
    override val isSolid = true
}