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
        Current state of screen input, show as world coordinates
     */
    var mouseX = 0f
    var mouseY = 0f
    var mouseDown = false

    /**
     * Describes which level the player is currently on
     * Use this if properties of something change with level
     */
    var levelNumber = 1
        set(value) {initLevel(this, levelNumber); field = value}

    /**
     * Values describing how big the level is, by default
     */
    var levelWidth = 1280
    var levelHeight = 720

    /**
     * A rectangle describing where the camera is currently looking at
     */
    var cameraBounds: Rect = Rect(0, 0, levelWidth, levelHeight)

    /**
     * Describes the amount of paint left in the brush
     */
    var maxPaint = 1000
    var paintUsed = 0



}