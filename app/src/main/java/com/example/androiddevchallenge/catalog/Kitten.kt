/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.catalog

data class Kitten(
    val id: String,
    val name: String,
    val imageName: String,
    val description: String,
    val gender: KittenGender,
    val age: KittenAge,
    val size: KittenSize,
    val color: List<String>,
    val race: String,
    val location: String,
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
