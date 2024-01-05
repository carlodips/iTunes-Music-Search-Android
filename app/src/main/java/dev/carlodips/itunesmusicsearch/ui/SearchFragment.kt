package dev.carlodips.itunesmusicsearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.carlodips.itunesmusicsearch.utils.createComposeView

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private val vm: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createComposeView {
            SearchScreen(
                uiModel = vm.uiModel,
                onSearchClick = vm::search
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
