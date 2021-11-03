package com.example.usapopulation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usapopulation.data.remote.models.DataRespObj
import com.example.usapopulation.data.remote.models.PopulationRespObj
import com.example.usapopulation.repository.PopulationRepository
import com.example.usapopulation.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopulationViewModel(
    private val populationRepository: PopulationRepository
): ViewModel() {

    sealed class PopulationState {
        class Success(val population: List<Population>): PopulationState()
        class Error(val errorMessage: String): PopulationState()
        object Loading: PopulationState()
        object Empty: PopulationState()
    }

    data class Population(
        val id: String,
        val nation: String,
        val population: String,
        val year: String
    )

    private var isSorted: Boolean = false
    private val _population = MutableStateFlow<PopulationState>(PopulationState.Empty)
    val population: StateFlow<PopulationState> = _population

    fun fetchPopulation(drilldowns: String, measures: String) = viewModelScope.launch(Dispatchers.IO) {
        _population.emit(PopulationState.Loading)
        when (val result: Resource<PopulationRespObj> = populationRepository.fetchPopulation(drilldowns, measures)) {
            is Resource.Success -> {
                val data: List<Population> = createPopulationList(result.fetchedData.data)
                _population.emit(PopulationState.Success(data))
            }
            is Resource.Error ->
                _population.emit(PopulationState.Error(result.message))
        }
    }

    fun sortPopulationByYear() {
        viewModelScope.launch {
            if (population.value is PopulationState.Success) {
                val populationValue: List<Population> = (population.value as PopulationState.Success).population
                val sortedPopulation: List<Population>
                if (isSorted) {
                    sortedPopulation = populationValue.sortedBy { it.year }
                    isSorted = false
                } else {
                    sortedPopulation = populationValue.sortedByDescending { it.year }
                    isSorted = true
                }
                _population.emit(PopulationState.Success(sortedPopulation))
            }
        }
    }

    private fun createPopulationList(dataList: List<DataRespObj>): List<Population> =
        // Shuffled сделан потому что приходит заранее упорядоченный массив, для боее наглядного примера
        dataList.shuffled().map {
            Population(
                it.idNation,
                it.nation,
                it.population.toString(),
                it.year
            )
        }
}