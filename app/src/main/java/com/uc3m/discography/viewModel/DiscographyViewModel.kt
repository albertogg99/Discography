package com.uc3m.discography.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc3m.discography.model.Discography
import com.uc3m.discography.model.DiscographyRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class DiscographyViewModel(private val repository: DiscographyRepository) : ViewModel() {

    val discography: MutableLiveData<Response<Discography>> = MutableLiveData()
    fun getDiscography(artist: String){
        viewModelScope.launch {
            val response = repository.getDiscography(artist)
            discography.value = response
        }

    }
}