package com.example.androiddevchallenge.catalog

data class Kitten(
    val id: String,
    val name: String,
    val description: String,
    val gender: KittenGender,
    val age: KittenAge,
    val size: KittenSize,
    val color: List<String>,
    val race: String,
    val localisation: String,
)

enum class KittenGender {
    MALE, FEMALE
}

enum class KittenSize {
    SMALL, NORMAL, BIG
}

data class KittenAge(
    val years: Int,
    val months: Int,
)