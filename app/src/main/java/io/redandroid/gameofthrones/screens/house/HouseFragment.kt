package io.redandroid.gameofthrones.screens.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.redandroid.gameofthrones.theme.GoTTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HouseFragment : Fragment() {

    private val houseViewModel : HouseViewModel by viewModels()

    private val navArgs: HouseFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = ComposeView(requireContext()).apply {
            setContent {
                GoTTheme {
                    Column {
                        val houseUiState by remember { houseViewModel.houseUiState }
                        HouseScreen(houseUiState = houseUiState)
                    }
                }
            }
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    houseViewModel.setHouseId(navArgs.houseId)
                }
            }
        }
    }
}