package com.example.usapopulation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usapopulation.R
import com.example.usapopulation.databinding.ItemPopulationBinding
import com.example.usapopulation.ui.PopulationViewModel
import com.example.usapopulation.utils.PopulationDiffCallback

class PopulationAdapter: ListAdapter<PopulationViewModel.Population, PopulationAdapter.PopulationViewHolder>(
    PopulationDiffCallback()
) {

    class PopulationViewHolder(val binding: ItemPopulationBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulationViewHolder =
        PopulationViewHolder(
            ItemPopulationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PopulationViewHolder, position: Int) {
        holder.binding.apply {
            val population: PopulationViewModel.Population = getItem(position)
            itemPopulationNationTV.text = root.context.getString(R.string.nation, population.nation)
            itemPopulationCountTV.text = root.context.getString(R.string.population, population.population)
            itemPopulationYearTV.text = root.context.getString(R.string.year, population.year)
        }
    }
}