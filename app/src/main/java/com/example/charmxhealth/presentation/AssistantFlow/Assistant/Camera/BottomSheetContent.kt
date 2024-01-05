package com.example.charmxhealth.presentation.AssistantFlow.Assistant.Camera

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.charmxhealth.MainViewModel

@Composable
fun BottomSheetContent(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    bitmaps: List<Bitmap>,
    modifier: Modifier = Modifier
) {
    if (bitmaps.isEmpty()) {
        Box(
            modifier = modifier
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("There are no photos yet")
        }
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
            contentPadding = PaddingValues(16.dp),
            modifier = modifier
        ) {
            items(bitmaps) { bitmap ->
                val context = LocalContext.current
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClick = {
                                if (bitmap != null) {
                                    viewModel.setSelectedBitmap(bitmap)
                                    navController.navigate("assistant_screen")
                                    Toast
                                        .makeText(context, "send $bitmap", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        )
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }
}

