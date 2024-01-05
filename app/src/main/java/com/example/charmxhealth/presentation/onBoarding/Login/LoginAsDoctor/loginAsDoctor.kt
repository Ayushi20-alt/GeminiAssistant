package com.example.charmxhealth.presentation.onBoarding.Login.LoginAsDoctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.charmxhealth.R
import com.example.charmxhealth.presentation.Dimens
import com.example.charmxhealth.presentation.onBoarding.OnBoardingEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginAsDoctor( event : (OnBoardingEvent)-> Unit
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var idNumber by remember { mutableStateOf("") }

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
            text = "Doctor Login",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                .copy(textAlign = TextAlign.Center),
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.height(Dimens.PageIndicatorWidth))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Name",
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = name,
                onValueChange = { name = it },
//                label = { Text("Name") },
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,containerColor = Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Phone Number",
                modifier = Modifier
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                //  label = { Text("Phone Number") },
                leadingIcon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) },
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,containerColor = Color.LightGray),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
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
                .padding(top=16.dp, start = 16.dp, end = 16.dp)
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
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "ID Number",
                modifier = Modifier
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = idNumber,
                onValueChange = { idNumber = it },
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
                Text(text = "Log In", color = Color.Red)
            }
        }
    }
}