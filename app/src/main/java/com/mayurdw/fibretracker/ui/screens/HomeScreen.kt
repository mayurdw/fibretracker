@file:OptIn(ExperimentalMaterial3Api::class)

package com.mayurdw.fibretracker.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onAddButtonClicked: () -> Unit = {}) {
    OutlinedButton(
        modifier = modifier,
        onClick = onAddButtonClicked
    ) {
        Text("Click Here")
    }
}
