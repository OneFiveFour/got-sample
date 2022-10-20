package io.redandroid.gameofthrones.screens.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import io.redandroid.gameofthrones.theme.GoTTheme

class HouseFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = ComposeView(requireContext()).apply {
            setContent {
                GoTTheme {
                    Column {
                        Text(
                            text = "House"
                        )
                    }
                }
            }
        }

        return rootView
    }

}