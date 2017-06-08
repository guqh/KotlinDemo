package com.john.kotlinanddatabindingdemo

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by guqh on 2017/6/8.
 */
class MyApp :Application(){

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}