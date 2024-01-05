package dev.carlodips.itunesmusicsearch.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import dev.carlodips.itunesmusicsearch.ui.theme.ITunesMusicSearchKotlinTheme

fun Fragment.createComposeView(
    setup: (ComposeView) -> Unit = {},
    content: @Composable () -> Unit = {}
): ComposeView {
    return ComposeView(requireContext()).apply {
        setup(this@apply)
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ITunesMusicSearchKotlinTheme {
                content()
            }
        }
    }
}