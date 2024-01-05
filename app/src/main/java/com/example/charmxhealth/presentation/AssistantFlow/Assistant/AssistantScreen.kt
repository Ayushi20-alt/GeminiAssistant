package com.example.charmxhealth.presentation.AssistantFlow.Assistant

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.charmxhealth.MainViewModel
import com.example.charmxhealth.R
import com.example.charmxhealth.presentation.Dimens.ExtraSmallPadding
import com.example.charmxhealth.presentation.Dimens.MediumPadding1
import com.example.charmxhealth.presentation.navigation.Route
import com.example.charmxhealth.ui.theme.CharmxHealthTheme
import com.example.webdigitaltask.domain.model.Gemini
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.OutputStream

@Composable
fun AppContent(navController: NavController, viewModel2: MainViewModel = hiltViewModel()) {
//    val selectedBitmapState = viewModel.selectedBitmap.collectAsState()
//
//    // Extract the value from State
//    val selectedBitmap = selectedBitmapState.value
//
//    if (selectedBitmap != null) {
//        // Now you can use selectedBitmap
//        Toast.makeText(LocalContext.current, "Selected bitmap: $selectedBitmap", Toast.LENGTH_SHORT).show()
//    } else {
//        Toast.makeText(LocalContext.current, "Selected bitmap is null", Toast.LENGTH_SHORT).show()
//    }
   // Toast.makeText(LocalContext.current, "$uri", Toast.LENGTH_SHORT).show()
    val viewModel: assistantViewModel = hiltViewModel()
    val appUiState = viewModel.uiState.collectAsState()
    // thses are coil step which is used when we want to get it as bitmap or drawable
    val coroutineScope = rememberCoroutineScope()
    val imageRequestBuilder = ImageRequest.Builder(LocalContext.current)
    val imageLoader = ImageLoader.Builder(LocalContext.current).build()

    HomeScreen(navController = navController, viewModel2 = viewModel2, uiState = appUiState.value){ inputText, selectedItems ->
        // we need to pass the bitmap to the viewmodel cause questioning needs it
        // for that we can use coil
        // we will do it with the help of image request builder
        coroutineScope.launch {
            // we will convert the selected it to the bitmap
            val bitmaps = selectedItems.mapNotNull {
                val imageRequest = imageRequestBuilder.data(it).size(size = 768).build()
                // firstly we need to create a image request
                // then imageRequest builder for creating request and that image request will be executed by image loader

                val imageResult = imageLoader.execute(imageRequest)
                if(imageResult is SuccessResult){
                   return@mapNotNull (imageResult.drawable as BitmapDrawable).bitmap
                }else{
                    return@mapNotNull null
                }
            }
            viewModel.questioning(userInput = inputText, selectedImages = bitmaps)
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel2: MainViewModel = hiltViewModel(), navController: NavController,
    uiState: HomeUiState = HomeUiState.Loading, onSendClicked: (String, List<Uri>)-> Unit
){
    val viewModel: assistantViewModel = hiltViewModel()
    val bitmap = viewModel2.getSelectedBitmap()
    val context = LocalContext.current
    val displayName = "my_image.jpg"

    var cameraUri: Uri? = bitmap?.let { bitmapToUri(context, it, displayName) }
//    SideEffect {
//        if(bitmap != null){
//            Toast.makeText(context, "selcted bitmap $bitmap", Toast.LENGTH_SHORT).show()
//        }
//    }

    val roomList = viewModel.roomstate.collectAsState(initial = emptyList())
    var userQues by rememberSaveable() {
       mutableStateOf("")
    }
    // remember savable store in bundle so we have to design our custom saver
    val imageUris = rememberSaveable(saver = UriCustomSaver()) {
        mutableStateListOf()
    }
//    if (cameraUri != null) {
//        imageUris.add(cameraUri)
//    }
    // we will need a image picker
    // we will create a pick media launcher there
    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ){imageUri->
        // adding them into imageUri
        imageUri?.let {
            if (!imageUris.contains(it)) {
                imageUris.add(it)
            }
        }

        // Adding the camera image URI if not null and not already present
        cameraUri?.let {
            if (!imageUris.contains(it)) {
                imageUris.add(it)
            }
        }

        cameraUri = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Your Assistant !!") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF7FB5E2),
                    titleContentColor = Color.Black
                ),
                navigationIcon = {

                },
                actions = {

                        // Your other action buttons
                        IconButton(
                            onClick = {
                                navController.navigate(Route.CameraScreen.route)
                            }
                        ) {
                            Icon(imageVector = Icons.Default.CameraEnhance, contentDescription = "Menu")
                        }
                }
            )
        },
        // bottom bar is just for the bottom portion like chatgpt
        bottomBar ={
            Column {
                Row(modifier = Modifier.padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    // Add Image Icon
                    IconButton(onClick = {
                        pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }, modifier = Modifier.padding(4.dp)) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Add Image"
                        )
                    }

                    // Input Field
                    // we will identify our state now
                    OutlinedTextField(
                        value = userQues, onValueChange = {
                            userQues = it
                        },
                        label = { Text( text = "User Input") },
                        placeholder = {
                            Text(
                                text = "Upload Image and Ask Questions",
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier.fillMaxWidth(0.84f)
                    )

                    // send button
                    IconButton(onClick = {
                        if (userQues.isNotBlank()) {
                            onSendClicked(userQues, imageUris)
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
                    }
                }
               // Color(0xFFDAE1E7)
                    // for diaplaying the selected image when selected
                    // for that we use animate visibility
                AnimatedVisibility(visible = imageUris.isNotEmpty() ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(Color(0xFFDAE1E7))
                    ) {
                        LazyRow(modifier = Modifier.padding(8.dp)) {
                            items(imageUris) { imageUri ->
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        model = imageUri,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .requiredSize(50.dp)
                                    )
                                    TextButton(onClick = { imageUris.remove(imageUri) }) {
                                        Text(text = "Remove", color = Color(0xFF7FB5E2))
                                    }
                                }
//                                // Display Bitmap if not null
//                                bitmap?.let {
//                                    Column(
//                                        verticalArrangement = Arrangement.Center,
//                                        horizontalAlignment = Alignment.CenterHorizontally
//                                    ) {
//                                        if (it != null) {
//                                            Image(
//                                                bitmap = it.asImageBitmap(),
//                                                contentDescription = "",
//                                                modifier = Modifier
//                                                    .padding(4.dp)
//                                                    .requiredSize(50.dp)
//                                            )
//                                        }
//                                        TextButton(onClick = { /* Handle removal of bitmap */ }) {
//                                            Text(text = "Remove", color = Color(0xFF7FB5E2))
//                                        }
//                                    }
//                                }
                            }
                        }
                    }
                }
            }
        }
    ){
        Column(modifier = Modifier
            .padding(it)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
       //     Toast.makeText(LocalContext.current, uiState.toString(), Toast.LENGTH_SHORT).show()
            
              when(uiState){
                  is HomeUiState.Initial -> {
                      ArticlesList(articles = roomList.value.asReversed())
                  }
                  is HomeUiState.Loading ->{
                      Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                          Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                              CircularProgressIndicator()
                          }
                      }
                  }
                  is HomeUiState.Success -> {
                      ArticlesList(articles = roomList.value.asReversed())
                  }
                  is HomeUiState.Error -> {
                      Card(modifier = Modifier
                          .padding(vertical = 16.dp)
                          .fillMaxWidth(),
                          shape = MaterialTheme.shapes.large,
                          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                          Text(modifier = Modifier.padding(16.dp), text = uiState.error)
                      }
                  }

              }
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles : List<Gemini>
){
    LazyColumn(
        modifier
            .fillMaxSize()
            .height(500.dp),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding)
    ){
        items(articles.size) {
            // if articles is not null we will show the article card for that
            val article = articles[it]
            examplecard(article)
        }
    }
}

@Composable
fun examplecard(
    car : Gemini
){
    val viewModel: assistantViewModel = hiltViewModel()
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .height(210.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.size(width = 60.dp, height = 80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.geminilogo),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
            ) {

                Surface(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.wrapContentSize(),
                    color = Color(0xFFD1D5E1)
                ) {
                    Text(
                        text = car.query,
                        fontSize =  12.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = car.promptText,
                    fontSize =  14.sp,
                    style = MaterialTheme.typography.titleLarge
                )

            }
            // button
            IconButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.deletearticle(car)
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun examplepreview(){
    CharmxHealthTheme {
        examplecard(Gemini(query = "what is this", promptText = "this is everything u want "))
    }
}

fun bitmapToUri(context: Context, bitmap: Bitmap, displayName: String, mimeType: String = "generateimage/jpeg"): Uri? {
    val resolver: ContentResolver = context.contentResolver
    val imageUri = getImageUri(context, displayName)

    try {
        val outputStream: OutputStream? = resolver.openOutputStream(imageUri)
        if (outputStream != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        outputStream?.close()
        return imageUri
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}

private fun getImageUri(context: Context, displayName: String): Uri {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
        put(MediaStore.Images.Media.MIME_TYPE, "generatedimage/jpeg")
    }

    val resolver = context.contentResolver
    val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    // Insert the image into the MediaStore
    val imageUri = resolver.insert(contentUri, contentValues)

    return imageUri ?: throw RuntimeException("Failed to create MediaStore image file")
}