package com.example.androiddevchallenge.app

import android.app.Application

class KittenApp: Application() {

    override fun onCreate() {
        super.onCreate()

        graph = KittenGraph()
    }

    companion object {
        private lateinit var graph: KittenGraph

        fun graph(): KittenGraph {
            return graph
        }
    }
}