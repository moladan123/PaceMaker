package com.example.myapplication

import android.content.Context
import android.graphics.Rect
import android.view.View

/**
 * Contains all of the information about the current game state
 */
class GameState(context: Context, view: View) {

    var resources = GameResourceManager(context)

    /**
        A hashmap containing all the gameobjects
        Maps the objectid to the corresponding object
     */
    var objectPool = HashMap<String, GameObject>()

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
    var levelNumber = 1

    /**
     * Information on the camera
     */
    val mWidth = view.width
    val mHeight = view.height
    var cameraBounds: Rect = Rect(0, 0, mWidth, mHeight)

}