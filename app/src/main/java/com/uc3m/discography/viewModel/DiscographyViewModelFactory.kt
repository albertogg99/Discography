package com.uc3m.discography.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uc3m.discography.model.DiscographyRepository

class DiscographyViewModelFactory(private val repository: DiscographyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscographyViewModel(repository) as T
    }
}