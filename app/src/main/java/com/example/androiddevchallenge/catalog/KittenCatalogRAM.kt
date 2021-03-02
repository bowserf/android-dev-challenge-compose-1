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

class KittenCatalogRAM : KittenCatalog {

    private val kittens = createKittenList()

    override fun getKittens(): List<Kitten> {
        return kittens
    }

    override fun getKitten(id: String): Kitten {
        return kittens.first { it.id == id }
    }

    private fun createKittenList(): List<Kitten> {
        return mutableListOf(
            Kitten(
                "kitten_id_1",
                "Lili",
                "lili",
                "Adorable big cat who will follow you everywhere you go until she has enough cuddle... but her limit is infinite!",
                KittenGender.FEMALE,
                KittenAge(5, 6),
                KittenSize.BIG,
                listOf("Brown", "White", "Black"),
                "european",
                "22 cité des cèdres, Quiberon, France"
            ),
            Kitten(
                "kitten_id_2",
                "Patou",
                "patou",
                "A small version of cat with a giant heart, who will give you all the love she has to you!",
                KittenGender.FEMALE,
                KittenAge(5, 6),
                KittenSize.SMALL,
                listOf("Gray", "Pink"),
                "european",
                "22 cité des cèdres, Quiberon, France"
            ),
            Kitten(
                "kitten_id_3",
                "Rocket",
                "rocket",
                "A small version of cat with a giant heart, who will give you all the love she has to you!",
                KittenGender.MALE,
                KittenAge(0, 8),
                KittenSize.NORMAL,
                listOf("Gray", "White"),
                "european",
                "22 cité des cèdres, Quiberon, France"
            ),
            Kitten(
                "kitten_id_4",
                "Misa",
                "misa",
                "A small version of cat with a giant heart, who will give you all the love she has to you!",
                KittenGender.FEMALE,
                KittenAge(5, 0),
                KittenSize.NORMAL,
                listOf("Black", "Brown", "Beige"),
                "european",
                "22 cité des cèdres, Quiberon, France"
            ),
            Kitten(
                "kitten_id_5",
                "Eugene",
                "eugene",
                "A small version of cat with a giant heart, who will give you all the love she has to you!",
                KittenGender.MALE,
                KittenAge(8, 0),
                KittenSize.NORMAL,
                listOf("Ginger"),
                "european",
                "22 cité des cèdres, Quiberon, France"
            ),
            Kitten(
                "kitten_id_6",
                "Moustache",
                "chat_lea",
                "A small version of cat with a giant heart, who will give you all the love she has to you!",
                KittenGender.FEMALE,
                KittenAge(10, 0),
                KittenSize.NORMAL,
                listOf("Black", "White"),
                "european",
                "22 cité des cèdres, Quiberon, France"
            )
        )
    }
}
