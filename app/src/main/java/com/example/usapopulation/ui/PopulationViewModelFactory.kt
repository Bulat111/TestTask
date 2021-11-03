package com.example.usapopulation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usapopulation.repository.PopulationRepository
import javax.inject.Inject

class PopulationViewModelFactory @Inject constructor(
    private val populationRepository: PopulationRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return PopulationViewModel(populationRepository) as T
    }
}