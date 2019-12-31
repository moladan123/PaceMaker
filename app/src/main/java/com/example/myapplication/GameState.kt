package com.example.myapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.DisplayMetrics

/**
 * Contains all of the information about the current game state
 */
class GameState(context: Context) {

    var resources = GameResourceManager(context)

    /**
        A hashmap containing all the gameobjects
        Maps the objectid to the corresponding object
     */
    var objectPool = ObjectPool()

    /**
        Controls, set to true by the view whenever a touch is inputted
                  set to false by the view whenever a touch ends
     */
    var moveLeft = false
    var moveRight = false

    /**
     * Describes which level the player is currently on
     * Use this if properties of something change with level
     */
    var levelNumber = 0
        private set

    /**
     * removes all gameobjects from the screen and initializes the next level
     */
    fun nextLevel() {
        levelNumber++
        initLevel(this, levelNumber)
    }

    /**
     * Values describing how big the level is, by default
     */
    var levelWidth = 1280
    var levelHeight = 720

    /**
     * A rectangle describing where the camera is currently looking at
     */
    var cameraBounds: Rect = Rect(0, 0, levelWidth, levelHeight)



}