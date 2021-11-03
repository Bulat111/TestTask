package com.example.usapopulation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.usapopulation.R
import com.example.usapopulation.adapters.PopulationAdapter
import com.example.usapopulation.databinding.FragmentPopulationBinding
import com.example.usapopulation.repository.PopulationRepository
import com.example.usapopulation.ui.PopulationFragment.Constants.drilldowns
import com.example.usapopulation.ui.PopulationFragment.Constants.measures
import com.example.usapopulation.utils.appComponent
import com.example.usapopulation.utils.toast
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PopulationFragment: BindingFragment<FragmentPopulationBinding>() {

    private object Constants {
        const val drilldowns: String = "Nation"
        const val measures: String = "Population"
    }

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPopulationBinding::inflate

    @Inject
    lateinit var populationRepository: PopulationRepository

    private val populationAdapter: PopulationAdapter = PopulationAdapter()
    private val populationVM: PopulationViewModel by viewModels(
        factoryProducer = { PopulationViewModelFactory(populationRepository) }
    )

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSortOnClickListener()
        setupRecyclerView()
        setupOnRefreshListener()

        populationVM.fetchPopulation(drilldowns, measures)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            populationVM.population.collect { populationState ->
                when(populationState) {
                    is PopulationViewModel.PopulationState.Error -> setupErrorState(populationState.errorMessage)
                    is PopulationViewModel.PopulationState.Loading -> setupLoadingState()
                    is PopulationViewModel.PopulationState.Success -> setupSuccessState(populationState.population)
                    else -> toast(getString(R.string.wrong_state))
                }
            }
        }
    }

    private fun setupOnRefreshListener() {
        binding.populationSwipeRefresh.setOnRefreshListener {
            populationVM.fetchPopulation(drilldowns, measures)
        }
    }

    private fun setupSortOnClickListener() {
        binding.populationSortButton.setOnClickListener {
            populationVM.sortPopulationByYear()
        }
    }

    private fun setupLoadingState() {
        binding.populationSwipeRefresh.isRefreshing = true
        binding.populationSortButton.visibility = View.GONE
    }

    private fun setupSuccessState(populationList: List<PopulationViewModel.Population>) {
        binding.populationSwipeRefresh.isRefreshing = false
        populationAdapter.submitList(populationList)
        binding.populationSortButton.visibility = View.VISIBLE
    }

    private fun setupErrorState(errorMessage: String) {
        binding.populationSwipeRefresh.isRefreshing = true
        binding.populationSortButton.visibility = View.GONE
        toast(errorMessage)
    }

    private fun setupRecyclerView() = binding.populationRecyclerView.apply {
        this.adapter = populationAdapter
        this.layoutManager = LinearLayoutManager(requireContext())
    }
}