package com.example.androiddevchallenge.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getDrawableRes(drawableName: String) =
    LocalContext.current.resources.getIdentifier(
        drawableName,
        "drawable",
        LocalContext.current.packageName
    )