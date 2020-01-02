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

const val WALL_WIDTH = 64f

/**
 * Adds a border around the level
 */
private fun border(gs: GameState) {
    // vertical walls
    for (i in 0 until gs.levelHeight/ WALL_WIDTH.toInt()) {
        gs.objectPool.add(Wall(gs, RectF(0f, i* WALL_WIDTH, WALL_WIDTH, (i + 1)* WALL_WIDTH)))
        gs.objectPool.add(Wall(gs, RectF(gs.levelWidth - WALL_WIDTH, i* WALL_WIDTH, gs.levelWidth*1f, (i + 1)* WALL_WIDTH)))
    }

    // horizontal walls
    for (i in 0 until gs.levelWidth/ WALL_WIDTH.toInt()) {
        gs.objectPool.add(Wall(gs, RectF(i* WALL_WIDTH, 0f, (i+1)* WALL_WIDTH, WALL_WIDTH)))
        gs.objectPool.add(Wall(gs, RectF(i* WALL_WIDTH, gs.levelHeight- WALL_WIDTH, (i+1)* WALL_WIDTH, gs.levelHeight*1f)))
    }

    gs.objectPool.add(Player(gs, RectF(428f, 428f, 492f, 492f)))

    gs.objectPool.add(Flag(gs, RectF(gs.levelWidth - 3* WALL_WIDTH, gs.levelHeight - 2* WALL_WIDTH,
                                    gs.levelWidth - 2 * WALL_WIDTH, gs.levelHeight - WALL_WIDTH)))
}
