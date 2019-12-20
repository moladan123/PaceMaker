package com.example.myapplication

import android.graphics.Canvas

interface GameObject{

    fun update(gameState: GameState) {}

    fun draw(canvas: Canvas)

}