package io.redandroid.gameofthrones.screens.houses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.redandroid.data.model.House
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.bindingadapters.isLoading
import io.redandroid.gameofthrones.bindingadapters.isRefreshing
import io.redandroid.gameofthrones.bindingadapters.setVisibilityGone
import io.redandroid.gameofthrones.common.ItemClickListener
import io.redandroid.gameofthrones.common.VerticalItemDecoration
import io.redandroid.gameofthrones.databinding.FragmentHousesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * This fragment displays a list of all houses in Game of Thrones.
 */
@AndroidEntryPoint
class HousesFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentHousesBinding? = null
    private val binding get() = _binding!!

    private val housesViewModel: HousesViewModel by viewModels()

    private val housesAdapter = HousesAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHousesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupStateListener()
        setupLoadingIndicator()

        collectUiState()
    }

    private fun setupRecyclerView() {
        binding.rvHouses.apply {
            setLayoutManager(LinearLayoutManager(context))
            setAdapter(housesAdapter)
            recyclerView.addItemDecoration(VerticalItemDecoration(R.dimen.rv_item_margin))
        }

        binding.splHouses.setOnRefreshListener {
            housesAdapter.refresh()
        }
    }

    /**
     * Uses the state listener of the HouseAdapter to inform the ViewModel
     * about state changed regarding errors while loading.
     */
    private fun setupStateListener() {
        housesAdapter.apply {
            addLoadStateListener { states ->
                housesViewModel.checkForErrors(states)
            }
        }
    }

    private fun setupLoadingIndicator() {
        housesAdapter.addLoadStateListener { loadStates ->
            // get all info for refresh/network indicators
            val hasNetworkTraffic = loadStates.mediator?.append is LoadState.Loading
            val hasNoItems = loadStates.refresh is LoadState.NotLoading && loadStates.append.endOfPaginationReached && housesAdapter.itemCount == 0

            // update UI
            isRefreshing(binding.splHouses, loadStates.refresh)
            isLoading(binding.pbNetworkActivity, hasNetworkTraffic)
            setVisibilityGone(binding.tvNoHouses, hasNoItems)

            // check for loading errors
            val combinedLoadStates = listOf(loadStates.append, loadStates.refresh, loadStates.prepend)
            if (combinedLoadStates.any { it is LoadState.Error }) {
                val message = combinedLoadStates.first { it is LoadState.Error }.toString()
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
            }

        }
    }

    /**
     * Main method to evaluate the UI state that is coming from the ViewModel.
     */
    private fun collectUiState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    housesViewModel.housesUiState.collectLatest { uiState ->
                        updateHouses(uiState)
                    }
                }
            }
        }
    }

    private suspend fun updateHouses(uiState: HousesUiState) {
        if (uiState.houses != null) {
            housesAdapter.submitData(uiState.houses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(clickedView: View, clickedItem: Any) {
        if (clickedItem !is House) return
        Timber.d("+++ House clicked: $clickedItem")
        val directions = HousesFragmentDirections.actionHousesFragmentToHouseFragment(clickedItem.id)
        findNavController().navigate(directions)
    }
}