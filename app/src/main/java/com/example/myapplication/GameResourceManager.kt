package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.HashMap


/**
 * Singleton class used to load resources, currently does not do any garbage collection
 * so if too many things are loaded at once it could cause memory leak
 */
class GameResourceManager(private val context: Context) {

    private var resources = HashMap<Int, Bitmap>()

    fun getResource(resourceID: Int): Bitmap {
        if (! resources.containsKey(resourceID)) {
            resources[resourceID] = BitmapFactory.decodeResource(context.resources, resourceID)
        }
        assert(resources[resourceID] != null)
        return resources[resourceID]!!
    }

}