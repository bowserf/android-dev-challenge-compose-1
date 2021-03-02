package com.example.androiddevchallenge.kitten_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.app.KittenApp

class KittenDetailsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val graph = KittenApp.graph()
        return KittenDetailsViewModel(
            graph.provideKittenCatalog()
        ) as T
    }
}