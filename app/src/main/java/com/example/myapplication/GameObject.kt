package com.example.myapplication

import android.graphics.Canvas

interface GameObject{

    fun update(millis: Int, objectPool :ArrayList<GameObject>) {}

    fun draw(canvas: Canvas)

}