package com.example.androiddevchallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.catalog.Kitten
import com.example.androiddevchallenge.catalog.KittenCatalog

class HomeViewModel(
    private val kittenCatalog: KittenCatalog
) : ViewModel() {

    private val kittens = MutableLiveData<List<Kitten>>()

    fun getKittens(): LiveData<List<Kitten>> {
        return kittens
    }
}