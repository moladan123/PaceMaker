package com.example.myapplication.gameobjects

import android.graphics.Canvas
import android.graphics.Rect
import com.example.myapplication.GameObject
import com.example.myapplication.GameSprite
import com.example.myapplication.GameState
import com.example.myapplication.R


class Player(override val gameState: GameState, override var bounds: Rect) : GameSprite() {
    override val imageID = R.drawable.person

    override fun update(gameState: GameState) {

    }
}