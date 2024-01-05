package com.example.charmxhealth.presentation.onBoarding.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.charmxhealth.R
import com.example.charmxhealth.presentation.Dimens.ExtraSmallPadding
import com.example.charmxhealth.presentation.Dimens.ExtraSmallPadding2
import com.example.charmxhealth.presentation.Dimens.IconSize
import com.example.charmxhealth.presentation.Dimens.IndicatorSize
import com.example.charmxhealth.presentation.Dimens.MediumPadding2
import com.example.charmxhealth.presentation.Dimens.PageIndicatorWidth
import com.example.charmxhealth.presentation.navigation.Route

@Composable
fun LoginScreen(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 30.dp, start = 30.dp, end = 30.dp)
                .size(100.dp)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight(),
            painter = painterResource(id = R.drawable.splashicon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Text(text = "Login",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold).copy(textAlign = TextAlign.Center),
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.height(PageIndicatorWidth))
        Text(text = "Login As Patient", color = colorResource(id = R.color.black))
        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
        Image(
            modifier = Modifier
                .size(120.dp)
                .padding(10.dp)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                           navController.navigate(Route.LoginAsPatientScreen.route)
                },
            painter = painterResource(id = R.drawable.user),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(IndicatorSize))
        Text(text = "Login As Doctor", color = colorResource(id = R.color.black))
        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
        Image(
            modifier = Modifier
                .size(120.dp)
                .padding(10.dp)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    navController.navigate(Route.LoginAsDoctorScreen.route)
                },
            painter = painterResource(id = R.drawable.doctor),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}