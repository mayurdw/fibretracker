package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun PreviewEditMenuScreen() {
    FibreTrackerTheme {
        EditMenuScreen()
    }
}

@Composable
fun EditMenuScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {},
            modifier = modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = modifier,
                text = "Add New Food"
            )
        }

        Spacer(modifier.height(16.dp))

        Button(onClick = {}, modifier = modifier.fillMaxWidth()) {
            Text(
                modifier = modifier,
                text = "Edit Existing Food"
            )
        }
    }
}
