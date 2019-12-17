package com.example.myapplication.gameobjects

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.example.myapplication.GameObject

class Wall(val paint: Paint, val r: Rect) : GameObject {

    override fun draw(canvas: Canvas) {
        canvas.drawRect(r, paint)
    }

}