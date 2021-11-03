package com.example.usapopulation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.usapopulation.ui.PopulationViewModel

class PopulationDiffCallback: DiffUtil.ItemCallback<PopulationViewModel.Population>() {

    override fun areItemsTheSame(
        oldItem: PopulationViewModel.Population,
        newItem: PopulationViewModel.Population
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PopulationViewModel.Population,
        newItem: PopulationViewModel.Population
    ): Boolean = oldItem == newItem
}