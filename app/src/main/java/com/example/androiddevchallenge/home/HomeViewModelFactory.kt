package com.example.androiddevchallenge.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.app.KittenApp

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val graph = KittenApp.graph()
        return HomeViewModel(
            graph.provideKittenCatalog()
        ) as T
    }
}