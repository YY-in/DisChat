package com.yyin.dischat.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yyin.opc.OverlappingPanels
import com.yyin.opc.OverlappingPanelsValue
import com.yyin.opc.rememberOverlappingPanelsState

@Composable
fun HomeScreen(
    onSettingsClick: () -> Unit,
    onPinsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val panelState = rememberOverlappingPanelsState(OverlappingPanelsValue.Closed)
    Surface(modifier = modifier) {
        OverlappingPanels(
            modifier = Modifier.fillMaxSize(),
            panelsState = panelState,
            panelStart = {/* TODO */},
            panelCenter = {/* TODO */},
            panelEnd = {/* TODO */}
        )
    }
}