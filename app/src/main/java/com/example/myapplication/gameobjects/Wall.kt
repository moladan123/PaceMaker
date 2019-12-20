package com.example.myapplication.gameobjects

import android.graphics.Rect
import com.example.myapplication.GameSprite
import com.example.myapplication.GameState
import com.example.myapplication.R

class Wall(override val gameState: GameState, override var bounds: Rect) : GameSprite() {
    override val imageID: Int = R.drawable.wall

}