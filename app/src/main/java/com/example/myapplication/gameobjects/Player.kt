package com.example.myapplication.gameobjects

import android.graphics.Canvas
import android.graphics.Rect
import com.example.myapplication.GameObject
import com.example.myapplication.GameSprite
import com.example.myapplication.GameState


class Player : GameSprite() {
    override val imageID: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override var bounds: Rect
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override val gameState: GameState
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun update(gameState: GameState) {

    }

    override fun draw(canvas: Canvas) {
        TODO("not implemented")
    }

}