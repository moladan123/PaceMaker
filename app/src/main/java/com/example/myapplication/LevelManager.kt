package com.example.myapplication

import android.graphics.RectF
import com.example.myapplication.gameobjects.*

fun initLevel(gs: GameState, level: Int) {
    gs.objectPool.clear()
    border(gs)

    // level specific initialization
    when(level) {
        1 -> {}
    }

}

/**
 * Adds a border around the level
 */
private fun border(gs: GameState) {
    // vertical walls
    for (i in 0 until gs.levelHeight/32) {
        gs.objectPool.add(Wall(gs, RectF(0f, i*32f, 32f, (i + 1)*32f)))
        gs.objectPool.add(Wall(gs, RectF(gs.levelWidth - 32f, i*32f, gs.levelWidth*1f, (i + 1)*32f)))
    }

    // horizontal walls
    for (i in 0 until gs.levelWidth/32) {
        gs.objectPool.add(Wall(gs, RectF(i*32f, 0f, (i+1)*32f, 32f)))
        gs.objectPool.add(Wall(gs, RectF(i*32f, gs.levelHeight-32f, (i+1)*32f, gs.levelHeight*1f)))
    }

    gs.objectPool.add(Player(gs, RectF(128f, 128f, 192f, 192f)))
}
