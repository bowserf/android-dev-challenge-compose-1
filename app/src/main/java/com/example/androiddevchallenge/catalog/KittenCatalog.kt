package com.example.androiddevchallenge.catalog

interface KittenCatalog {

    fun getKittens(): List<Kitten>

    fun getKitten(id: String): Kitten
}