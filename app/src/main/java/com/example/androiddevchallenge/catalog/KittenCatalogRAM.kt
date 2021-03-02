package com.example.androiddevchallenge.catalog

class KittenCatalogRAM : KittenCatalog {

    private val kittens = createKittenList()

    override fun getKittens(): List<Kitten> {
        return kittens
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