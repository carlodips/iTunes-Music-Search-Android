package dev.carlodips.itunesmusicsearch.ui.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.carlodips.itunesmusicsearch.R
import dev.carlodips.itunesmusicsearch.utils.createComposeView
import dev.carlodips.itunesmusicsearch.utils.observeEvent

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val vm: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createComposeView {
            SearchScreen(
                input = vm.input,
                uiState = vm.uiState,
                onSearchClick = vm::search
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.ldNavigateToDetails.observeEvent(viewLifecycleOwner) {
            val bundle = bundleOf("result" to it)
            findNavController().navigate(
                R.id.action_searchFragment_to_detailsFragment,
                bundle
            )
        }
    }
}
