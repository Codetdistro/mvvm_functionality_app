package com.example.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ModelFactory(private val repo:Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel::class.java)) {
            return viewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}