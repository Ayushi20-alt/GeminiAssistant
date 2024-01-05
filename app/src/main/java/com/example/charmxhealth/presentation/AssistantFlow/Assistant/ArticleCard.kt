package com.example.charmxhealth.presentation.AssistantFlow.Assistant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.charmxhealth.R

@Composable
fun ArticleCard(
    state: AssistantState,
    index : Int,
    onEvent: (AssistantEvent) -> Unit
){
    Card(modifier = Modifier
        .padding(vertical = 16.dp)
        .fillMaxWidth(),
        shape = MaterialTheme.shapes.large) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.geminilogo),
                    contentDescription = null, // Set to null if the image is decorative
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 30.dp),
                    contentScale = ContentScale.Crop
                )

                // Spacer for spacing between image and text
                Spacer(modifier = Modifier.width(16.dp))

                // Text
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 26.dp), text = state.articles[index].query)
           //     Spacer(modifier = Modifier.width(36.dp))
                IconButton(onClick = { onEvent(AssistantEvent.DeleteArticle(state.articles[index])) }) {
                    Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete Item",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }

            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)  //fill the max height
                    .width(1.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.geminilogo),
                    contentDescription = null, // Set to null if the image is decorative
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )

                // Spacer for spacing between image and text
                Spacer(modifier = Modifier.width(16.dp))

                // Text
                Text(modifier = Modifier.padding(16.dp), text = state.articles[index].promptText)
            }
        }

    }
}