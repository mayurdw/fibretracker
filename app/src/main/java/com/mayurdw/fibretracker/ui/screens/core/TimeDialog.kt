package com.mayurdw.fibretracker.ui.screens.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeDialog(
    hour: Int,
    min: Int,
    onDismiss: () -> Unit,
    onConfirmation: (
            hour: Int,
            min: Int
        ) -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = hour,
        initialMinute = min,
        is24Hour = true
    )

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePicker(
                state = timePickerState,
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onDismiss) {
                    Text("Dismiss")
                }

                Button(onClick = {
                    onConfirmation(
                        timePickerState.hour, timePickerState.minute
                    )
                }) {
                    Text("Confirm")
                }
            }
        }
    }
}


@PreviewLightDark
@Composable
fun PreviewTimeDialog() {
    FibreTrackerTheme {
        TimeDialog(
            hour = 13,
            min = 34,
            onDismiss = {}
        ) { _, _ -> }
    }
}