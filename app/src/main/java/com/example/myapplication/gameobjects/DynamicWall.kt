package com.example.myapplication.gameobjects

import android.graphics.RectF
import com.example.myapplication.GameObject
import com.example.myapplication.GameState
import com.example.myapplication.R
import kotlin.math.floor

// The length of an individual piece of wall
private val BLOCK_WIDTH = 16.0

class DynamicWall(override val gameState: GameState) : GameObject() {

    override val imageID = R.drawable.static_wall
    override val isStatic = true
    override val isSolid = true
    override var bounds = RectF()

    private val blocks = HashMap<Int, RectF>()
    override fun checkCollide(other: RectF): Boolean {
        for (box in blocks.values) {
            if (other.intersects(box.left, box.top, box.right, box.bottom)) {
                return true
            }
        }
        return false
    }

    override fun update(gameState: GameState) {

        println(String.format("input x = %2f, y = %2f", gameState.mouseX, gameState.mouseY))

        if (gameState.mouseDown) {

            val blockIndex: Int =
                    (floor(gameState.mouseX / BLOCK_WIDTH) * floor(gameState.levelWidth / BLOCK_WIDTH)
                            + floor(gameState.mouseY / BLOCK_WIDTH))
                            .toInt()

            gameState.mouseDown = false

            if (blocks.containsKey(blockIndex)) { // remove the block that was tapped on
                blocks.remove(blockIndex)
            } else { // add in the new block
                blocks[blockIndex] = RectF((floor(gameState.mouseX / BLOCK_WIDTH)* BLOCK_WIDTH).toFloat(),
                        (floor(gameState.mouseY / BLOCK_WIDTH) * BLOCK_WIDTH).toFloat(),
                        (floor(gameState.mouseX / BLOCK_WIDTH) * (BLOCK_WIDTH + 1)).toFloat(),
                        (floor(gameState.mouseY / BLOCK_WIDTH) * (BLOCK_WIDTH + 1)).toFloat() )
            }

        }
    }
}