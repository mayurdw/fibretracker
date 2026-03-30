package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mayurdw.fibretracker.R
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme


enum class PoopQuality(val image: Int, val desc: String) {
    TYPE_7(
        R.drawable.type_7, "Separate Hard Lumps, like little pebbles (Hard to pass)"
    ),
    TYPE_6(
        R.drawable.type_6, "Hard and lumpy and starting to resemble a sausage"
    ),
    TYPE_5(
        R.drawable.type_5, "Sausage-shaped with cracks on the surface"
    ),
    TYPE_4(
        R.drawable.type_4, "Thinner and more snakelike, plus smooth and soft"
    ),
    TYPE_3(
        R.drawable.type_3, "Soft blobs with clear cut edges"
    ),
    TYPE_2(
        R.drawable.type_2, "Fluffy, mushy pieces with ragged edges"
    ),
    TYPE_1(
        R.drawable.type_1, "Watery with no solid pieces"
    )
}

@Composable
fun PoopQualityScreen() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        items(PoopQuality.entries.toTypedArray()) {
            Card(
                colors = CardDefaults.elevatedCardColors(
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Image(
                    modifier = Modifier
                        .width(172.dp)
                        .height(96.dp),
                    painter = painterResource(it.image),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(all = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    text = it.desc,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewPoopQualityScreen() {
    FibreTrackerTheme {
        PoopQualityScreen()
    }
}