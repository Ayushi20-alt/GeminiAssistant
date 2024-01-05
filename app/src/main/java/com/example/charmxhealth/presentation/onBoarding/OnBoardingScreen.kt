package com.example.charmxhealth.presentation.onBoarding

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.charmxhealth.R
import com.example.charmxhealth.presentation.Dimens.IndicatorSize
import com.example.charmxhealth.presentation.Dimens.MediumPadding1
import com.example.charmxhealth.presentation.Dimens.MediumPadding2
import com.example.charmxhealth.presentation.Dimens.SmallIconSize
import com.example.charmxhealth.presentation.navigation.Route
import com.example.charmxhealth.ui.theme.CharmxHealthTheme
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun onBoardingScreen(
    event : (OnBoardingEvent)-> Unit
){
   Column(horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center,
       modifier = Modifier.fillMaxSize()) {
       Image(
           modifier =  Modifier
               .padding(30.dp)
               .wrapContentSize(Alignment.Center)
               .fillMaxWidth()
               .wrapContentHeight(),
           painter = painterResource(id = R.drawable.onboardinglogo),
           contentDescription = null,
           contentScale = ContentScale.Crop
       )
       Image(
           modifier = Modifier
               .size(120.dp)
               .padding(10.dp)
               .wrapContentSize(Alignment.Center)
               .fillMaxWidth()
               .wrapContentHeight(),
           painter = painterResource(id = R.drawable.splashicon),
           contentDescription = null,
           contentScale = ContentScale.Crop,
       )
       Spacer(modifier = Modifier.height(SmallIconSize))
       Text(
           text = "An application to help patients and doctors to access medical records via a \n" +
                   "decentralized database",
           modifier = Modifier.padding(horizontal = MediumPadding2),
           style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold).copy(textAlign = TextAlign.Center),
           color = colorResource(id = R.color.black)
       )
       Spacer(modifier = Modifier.height(IndicatorSize))
       TextButton(
           onClick = { event(OnBoardingEvent.saveAppEntry) },
           modifier = Modifier
               .padding(start = 100.dp, end = 120.dp, top = 16.dp)
               .fillMaxWidth().border(
                       width = 2.dp,
                       color = Color.Red,
                       shape = RoundedCornerShape(28.dp)
               ).clip(RoundedCornerShape(28.dp)),contentPadding = PaddingValues(0.dp)
       ) {
           Row(
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center
           ) {
               Text(text = "Get Started", color = Color.Red)
               Spacer(modifier = Modifier.width(4.dp))
               Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.Red)
           }
       }
   }
}

//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun OnBoardingPreview(){
//    CharmxHealthTheme {
//        onBoardingScreen()
//    }
//}