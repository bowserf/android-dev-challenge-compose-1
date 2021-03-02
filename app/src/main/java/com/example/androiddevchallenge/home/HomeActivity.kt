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
package com.example.androiddevchallenge.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.catalog.Kitten
import com.example.androiddevchallenge.catalog.KittenAge
import com.example.androiddevchallenge.catalog.KittenGender
import com.example.androiddevchallenge.catalog.KittenSize
import com.example.androiddevchallenge.ui.theme.MyTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import com.example.androiddevchallenge.R


class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels { HomeViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val kittens = viewModel.getKittens().observeAsState(listOf())
            MyTheme {
                MyApp(kittens.value)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(kittens: List<Kitten>) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Text(
                text = stringResource(id = R.string.home_screen_title),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.internal_design_system_text_main),
                modifier = Modifier
                    .padding(16.dp)
            )
            KittenList(kittens)
        }
    }
}

@Composable
private fun KittenList(kittens: List<Kitten>) {
    val kittenPairs = createKittenPairsList(kittens)
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = kittenPairs) { kittenPair ->
            Row {
                KittenCard(
                    kitten = kittenPair.first,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (kittenPair.second != null) {
                    KittenCard(
                        kitten = kittenPair.second!!,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun KittenCard(
    kitten: Kitten,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            val image = getDrawableRes(kitten.imageName)
            Image(
                painter = painterResource(image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = kitten.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Breed:",
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = kitten.race
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                when (kitten.gender) {
                    KittenGender.FEMALE -> {
                        KittenDescriptionCell(
                            "Female",
                            colorResource(id = R.color.home_kitten_card_text_female),
                            colorResource(id = R.color.home_kitten_card_text_bg_female)
                        )
                    }
                    KittenGender.MALE -> {
                        KittenDescriptionCell(
                            "Male",
                            colorResource(id = R.color.home_kitten_card_text_male),
                            colorResource(id = R.color.home_kitten_card_text_bg_male)
                        )
                    }
                }
                val kittenAge = when (kitten.age.years) {
                    0 -> LocalContext.current.resources.getQuantityString(
                        R.plurals.home_screen_kitten_card_age_months,
                        kitten.age.months,
                        kitten.age.months
                    )
                    else -> LocalContext.current.resources.getQuantityString(
                        R.plurals.home_screen_kitten_card_age_years,
                        kitten.age.years,
                        kitten.age.years
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                KittenDescriptionCell(
                    kittenAge,
                    colorResource(id = R.color.internal_design_system_text_main),
                    Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun KittenDescriptionCell(
    text: String,
    textColor: Color,
    backgroundColor: Color
) {
    Text(
        text = text,
        color = textColor,
        fontSize = 12.sp,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
fun KittenCardPreview() {
    val kitten = Kitten(
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
    )
    KittenCard(kitten)
}

@Composable
private fun getDrawableRes(drawableName: String) =
    LocalContext.current.resources.getIdentifier(
        drawableName,
        "drawable",
        LocalContext.current.packageName
    )

private fun createKittenPairsList(kittens: List<Kitten>): MutableList<Pair<Kitten, Kitten?>> {
    val kittenPairs = mutableListOf<Pair<Kitten, Kitten?>>()
    for (i in 0 until kittens.size / 2) {
        kittenPairs.add(Pair(kittens[i * 2], kittens[i * 2 + 1]))
    }
    if (kittens.size % 2 != 0) {
        kittenPairs.add(Pair(kittens.last(), null))
    }
    return kittenPairs
}
