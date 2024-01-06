package dev.carlodips.itunesmusicsearch.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.carlodips.itunesmusicsearch.utils.createComposeView

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val vm: DetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createComposeView {
            DetailsScreen(uiModel = vm.uiModel)
        }
    }
}