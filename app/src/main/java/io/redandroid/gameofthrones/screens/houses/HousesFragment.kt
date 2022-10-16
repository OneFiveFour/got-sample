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
import dagger.hilt.android.AndroidEntryPoint
import io.redandroid.data.model.House
import io.redandroid.gameofthrones.common.ItemClickListener
import io.redandroid.gameofthrones.databinding.FragmentHousesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

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

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    housesViewModel.housesUiState.collectLatest { uiState ->
                        if (uiState.errorMessage.isNotEmpty()) {
                            setErrorState(uiState.errorMessage)
                        } else if (uiState.isLoading) {
                            setLoadingState()
                        } else {
                            setListState(uiState.houses)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListState(houses: List<House>) {
        (binding.rvHouses.adapter as HousesAdapter).submitList(houses)
    }

    private fun setLoadingState() {

    }

    private fun setErrorState(errorMessage: String) {

    }

    override fun onItemClicked(clickedView: View, clickedItem: Any) {
        if (clickedItem !is House) return
        Timber.d("+++ House clicked: $clickedItem")
    }
}