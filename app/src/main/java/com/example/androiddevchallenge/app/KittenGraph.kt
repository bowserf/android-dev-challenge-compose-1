package com.example.androiddevchallenge.app

import com.example.androiddevchallenge.catalog.KittenCatalogModule

class KittenGraph {

    private val kittenCatalog = KittenCatalogModule().createKittenCatalog()

    fun provideKittenCatalog() = kittenCatalog
}