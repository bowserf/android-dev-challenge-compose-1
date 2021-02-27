package com.example.androiddevchallenge.catalog

class KittenCatalogModule {

    fun createKittenCatalog(): KittenCatalog {
        return KittenCatalogRAM()
    }
}