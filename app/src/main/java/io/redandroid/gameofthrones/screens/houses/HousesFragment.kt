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
import androidx.paging.PagingData
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

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    housesViewModel.getHouses().collectLatest { pagingData ->
                        (binding.rvHouses.adapter as HousesAdapter).submitData(pagingData)
                    }
                }
            }
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