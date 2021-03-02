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
package com.example.androiddevchallenge.kitten_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.catalog.Kitten
import com.example.androiddevchallenge.catalog.KittenAge
import com.example.androiddevchallenge.catalog.KittenGender
import com.example.androiddevchallenge.catalog.KittenSize
import com.example.androiddevchallenge.utils.getDrawableRes
import java.lang.IllegalStateException

class KittenDetailsActivity : AppCompatActivity() {

    private val viewModel: KittenDetailsViewModel by viewModels { KittenDetailsViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kittenId = extractKittenId()
        viewModel.setKittenId(kittenId)

        setContent {
            val kitten = viewModel.getLiveKitten().observeAsState(viewModel.getLiveKitten().value)
            KittenDetails(kitten.value!!)
        }
    }

    @Composable
    fun KittenDetails(kitten: Kitten) {
        Surface(
            color = colorResource(id = R.color.kitten_details_screen_bottom_bg),
            modifier = Modifier.fillMaxHeight()
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                ) {
                    val image = getDrawableRes(kitten.imageName)
                    Image(
                        painter = painterResource(image),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 16.dp,
                                    bottomEnd = 16.dp
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = kitten.name,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.kitten_details_screen_location),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = kitten.location)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            KittenInformationCard(
                                stringResource(id = R.string.kitten_screen_details_information_sex),
                                kitten.gender.toString()
                            )
                            Spacer(modifier = Modifier.width(8.dp))
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
                            KittenInformationCard(
                                stringResource(id = R.string.kitten_screen_details_information_age),
                                kittenAge
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            KittenInformationCard(
                                stringResource(id = R.string.kitten_screen_details_information_breed),
                                kitten.race
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(id = R.string.kitten_screen_details_details),
                            color = colorResource(id = R.color.internal_design_system_text_main),
                            fontSize = 18.sp
                        )
                        Text(
                            text = kitten.description,
                            color = colorResource(id = R.color.internal_design_system_text_secondary)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .defaultMinSize(minHeight = 100.dp)
                        .padding(16.dp)
                ) {
                    val (isFavorite, setIsFavorite) = remember { mutableStateOf(false) }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = colorResource(id = R.color.kitten_details_screen_favorite_bg))
                            .clickable { setIsFavorite(!isFavorite) }
                    ) {
                        Image(
                            painter = painterResource(id = if (isFavorite) R.drawable.kitten_details_screen_favorite else R.drawable.kitten_details_screen_no_favorite),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* nothing to do */ },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.kitten_screen_details_adopt_me_btn)
                        ),
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.kitten_screen_details_adopt_me),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun KittenInformationCard(title: String, information: String) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.kitten_details_screen_information_bg),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(10.dp)
        ) {
            Text(
                text = title,
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = information,
                color = colorResource(id = R.color.internal_design_system_text_main),
                fontWeight = FontWeight.Bold
            )
        }
    }

    private fun extractKittenId(): String {
        if (intent.extras?.containsKey(KITTEN_ID_EXTRA_KEY) == true) {
            return intent.extras!!.getString(KITTEN_ID_EXTRA_KEY)!!
        }
        throw IllegalStateException("You must call \"startActivity(Context, String)\" to launch this activity.")
    }

    @Preview
    @Composable
    fun KittenDetailsPreview() {
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
        KittenDetails(kitten = kitten)
    }

    companion object {

        private const val KITTEN_ID_EXTRA_KEY = "kitten_details.extra.key"

        fun startActivity(context: Context, kittenId: String) {
            val intent = Intent(context, KittenDetailsActivity::class.java)
            intent.putExtra(KITTEN_ID_EXTRA_KEY, kittenId)
            context.startActivity(intent)
        }
    }
}
