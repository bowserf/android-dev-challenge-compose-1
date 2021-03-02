package com.example.androiddevchallenge.kitten_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.catalog.Kitten
import com.example.androiddevchallenge.catalog.KittenCatalog

class KittenDetailsViewModel(
    private val kittenCatalog: KittenCatalog
) : ViewModel() {

    private var kitten = MutableLiveData<Kitten>()

    fun getLiveKitten(): LiveData<Kitten> {
        return kitten
    }

    fun setKittenId(kittenId: String) {
        kitten.value = kittenCatalog.getKitten(kittenId)
    }
}