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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.catalog.Kitten
import com.example.androiddevchallenge.catalog.KittenCatalog
import com.example.androiddevchallenge.event.Event

class HomeViewModel(
    private val kittenCatalog: KittenCatalog
) : ViewModel() {

    private val kittens = MutableLiveData<List<Kitten>>()
    private val navigateToKittenDetails = MutableLiveData<Event<String>>()

    init {
        kittens.value = kittenCatalog.getKittens()
    }

    fun onClickKitten(kittenId: String) {
        navigateToKittenDetails.value = Event(kittenId)
    }

    fun getNavigateToKittenDetails() = navigateToKittenDetails

    fun getKittens() = kittens
}
