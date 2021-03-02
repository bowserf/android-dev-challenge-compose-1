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