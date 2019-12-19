package com.example.myapplication

import android.graphics.Canvas

interface GameObject{

    fun update(objectPool :Map<String, GameObject>) {}

    fun draw(canvas: Canvas)

}