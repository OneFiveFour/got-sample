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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.redandroid.data.model.House
import io.redandroid.gameofthrones.common.ItemClickListener
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHousesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHouses.adapter = HousesAdapter(this)
        setupStateListener()
        collectUiState()
    }

    /**
     * Uses the state listener of the HouseAdapter to inform the ViewModel
     * about state changed regarding errors while loading.
     */
    private fun setupStateListener() {
        (binding.rvHouses.adapter as HousesAdapter).apply {
            addLoadStateListener { states ->
                housesViewModel.checkForErrors(states)
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
                        updateError(uiState)
                    }
                }
            }
        }
    }

    private fun updateError(uiState: HousesUiState) {
        Timber.d("+++ got uiState with Error: ${uiState.errorMessage}")
        if (uiState.errorMessage.isNotEmpty()) {
            Snackbar.make(binding.root, uiState.errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private suspend fun updateHouses(uiState: HousesUiState) {
        if (uiState.houses != null) {
            (binding.rvHouses.adapter as HousesAdapter).submitData(uiState.houses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(clickedView: View, clickedItem: Any) {
        if (clickedItem !is House) return
        Timber.d("+++ House clicked: $clickedItem")
    }
}