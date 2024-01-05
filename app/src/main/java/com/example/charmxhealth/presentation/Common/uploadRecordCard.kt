package com.example.charmxhealth.presentation.Common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charmxhealth.R
import com.example.charmxhealth.ui.theme.CharmxHealthTheme

@Composable
fun uploadRecordCard(article: aricleCardDataClass, onItemClick:(article: aricleCardDataClass)-> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(180.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true, color = Color.Black),
                onClick = {onItemClick(article)}
            ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 10.dp
//        )
    ) {
       Box {
           Image(
               painter = painterResource(article.imageUrl),
               contentDescription = null,
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .height(180.dp)
                   .fillMaxWidth()
                   .clip(shape = MaterialTheme.shapes.medium)
           )

           Card(
               modifier = Modifier
                   .fillMaxWidth()
                   .height(40.dp)
                   .background(Color.Black.copy(alpha = 0.4f))
           ) {
               Column(
                   modifier = Modifier
                       .fillMaxWidth().padding(10.dp),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   // Text on top of the image
                   Text(
                       text = "Your Text Here",
                       style = MaterialTheme.typography.bodyMedium,
                       color = Color.White
                   )
               }
           }
       }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun uploadScreenPreview(){
    CharmxHealthTheme {
        uploadRecordCard(
            aricleCardDataClass(
                title = "Article ",
                imageUrl = R.drawable.ic_launcher_background
            ),
            onItemClick = {}
        )
    }
}