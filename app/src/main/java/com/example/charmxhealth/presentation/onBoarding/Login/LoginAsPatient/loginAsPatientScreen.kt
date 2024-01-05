package com.example.charmxhealth.presentation.onBoarding.Login.LoginAsPatient

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.charmxhealth.R
import com.example.charmxhealth.presentation.Dimens
import com.example.charmxhealth.presentation.Dimens.ArticleCardSize
import com.example.charmxhealth.presentation.Dimens.PageIndicatorWidth
import com.example.charmxhealth.presentation.navigation.Route
import com.example.charmxhealth.presentation.onBoarding.OnBoardingEvent
import com.example.charmxhealth.ui.theme.CharmxHealthTheme

@OptIn( ExperimentalMaterial3Api::class)
@Composable
fun loginAsPatientScreen(
    event : (OnBoardingEvent)-> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var referenceNumber by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
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
        Text(
            text = "Patient Login",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                .copy(textAlign = TextAlign.Center),
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.height(PageIndicatorWidth))

        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "E-Mail ID",
                modifier = Modifier
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = email,
                onValueChange = { email = it },
            //    label = { Text("Email") },
                leadingIcon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) },
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,containerColor = Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
//            keyboardActions = KeyboardActions(onNext = {
//                LocalSoftwareKeyboardController.current?.hide()
//            }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Password",
                modifier = Modifier
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                //    label = { Text("Email") },
                leadingIcon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) },
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,containerColor = Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
//            keyboardActions = KeyboardActions(onNext = {
//                LocalSoftwareKeyboardController.current?.hide()
//            }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }


        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Patient Reference Number",
                modifier = Modifier
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = referenceNumber,
                onValueChange = { referenceNumber = it },
          //      label = { Text("Patient Reference Number") },
                leadingIcon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) },
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,containerColor = Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        TextButton(
            onClick = {event(OnBoardingEvent.saveAppEntry)},
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, top = 16.dp)
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(28.dp)
                )
                .clip(RoundedCornerShape(28.dp)),contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Log In", color = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginAsPatientScreenpreview(){
    CharmxHealthTheme {
        loginAsPatientScreen(event = {})
    }
}