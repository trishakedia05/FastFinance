package com.example.finance.screens.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_TEXT
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.finance.R
import com.example.finance.data.DataOrException
import com.example.finance.model.Article
import com.example.finance.model.NewsStatus
import com.example.finance.navigation.allScreens
import com.example.finance.utils.DateFormatted
import com.example.finance.utils.constants
import com.example.finance.utils.provider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    ShowData(navController = navController, mainViewModel = mainViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(mainViewModel: MainViewModel, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Flash Finance",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(allScreens.SettingScreen.name) }) {
                Icon(Icons.Filled.Star, contentDescription = "Settings")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFFDDB87D),)
    )
}

@Composable
fun BottomBar(navController: NavController) {
    BottomAppBar(
        modifier = Modifier.padding(2.dp),

        containerColor  = Color.White,
        contentColor = Color.Black
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBarIcon(
                icon = Icons.Filled.ArrowBack,
                onClick = { navController.popBackStack() },
                contentDescription = "Back"
            )
            BottomBarIcon(
                icon = Icons.Filled.Share,
                onClick = { /* Handle share action */ },
                contentDescription = "Share"
            )
            BottomBarIcon(
                icon = Icons.Outlined.Star,
                onClick = { /* Handle star action */ },
                contentDescription = "Star"
            )
        }
    }
}

@Composable
fun BottomBarIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.Black // Adjust icon tint color as needed
        )
    }
}
@Composable
fun ShowData(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    val myData = produceState<DataOrException<NewsStatus, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getAllNews("in", "business", constants.apiKey)
    }.value

    if (myData.loading==true) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Loading News..")
                Spacer(modifier = Modifier.size(2.dp))
                CircularProgressIndicator(modifier = Modifier.size(40.dp), color = Color.White)
            }
        }
    } else if (myData.data != null) {
        Log.d("mydata", "${myData.data}")
        AppLayout(dataToShow = myData.data!!, navController = navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppLayout(dataToShow: NewsStatus, navController: NavController) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    Surface(modifier = Modifier, color = Color.White) {
        LazyRow(
            modifier = Modifier.padding(5.dp),
            contentPadding = PaddingValues(horizontal = 1.dp, vertical = 5.dp),
            state = lazyListState,
            flingBehavior = snapBehavior
        ) {
            items(items = dataToShow.articles.filter { it.description != null }) { item: Article ->
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(3.dp, Color.Black),
                            RoundedCornerShape(size = 18.dp)
                        )
                        .clip(shape = RoundedCornerShape(18.dp))
                        .fillMaxHeight()
                        .width(350.dp)
                ) {
                    Structure(item = item, navController = navController)
                    Log.d("all","${dataToShow.articles.size}")
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Structure(item: Article, navController: NavController) {
    val sendIntent = Intent().apply {
        action = ACTION_SEND
        putExtra(EXTRA_TEXT, item.url)
        type = "text/plain"
    }
    val context = LocalContext.current
    val shareLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == ComponentActivity.RESULT_OK) {
            Toast.makeText(context, "Shared successfully", Toast.LENGTH_SHORT).show()
        }
    }

    Surface(modifier = Modifier.fillMaxHeight(), color = Color.White) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                color = Color.White
            ) {
                if(item.urlToImage !=null){
                    Image(
                        painter = rememberAsyncImagePainter(model = item.urlToImage),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "News Image",
                        modifier = Modifier
                            .height(210.dp)
                            .fillMaxWidth()
                            .padding(2.dp)
                            .clip(shape = RoundedCornerShape(size = 10.dp)),
                        alignment = Alignment.TopCenter
                    )
                }
                else{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color.LightGray)
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Image Available",
                            color = Color.Black,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .border(
                        BorderStroke(2.dp, Color.LightGray),
                        RoundedCornerShape(size = 10.dp)
                    )
                    .padding(3.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.size(3.dp))
                    //headline
                    Text(
                        modifier = Modifier.padding(horizontal = 13.dp, vertical = 3.dp),
                        text = item.title.substringBeforeLast("-").substringBeforeLast("|"),
                        color = Color.Black,
                        fontWeight = FontWeight(1000),
                        fontFamily = FontFamily(Font(googleFont = GoogleFont("PT Serif"), fontProvider = provider)),
                        fontSize = 20.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 13.dp, vertical = 1.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        //date
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = DateFormatted(item.publishedAt.substringBefore("T")),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(googleFont = GoogleFont("PT Serif"), fontProvider = provider)),
                            textAlign = TextAlign.Right
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Divider(modifier = Modifier.padding(horizontal = 6.dp), thickness = 2.dp, color = Color.LightGray)
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                        text = item.description + ".",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(googleFont = GoogleFont("Crimson Text"), fontProvider = provider)),
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .border(
                                BorderStroke(1.dp, Color.Gray),
                                shape = RectangleShape
                            )
                            .fillMaxWidth()
                            .padding(3.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(3.dp),
                            text = "AI Advice: ",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.padding(3.dp),
                            text = "The following news article has the " +
                                    "following significance in the share market. This is not financial advice, " +
                                    "but is my inference of the news.",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            //icons
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .border(
                        BorderStroke(1.dp, Color.Gray),
                        shape = RectangleShape
                    ),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = null,
                                tint = Color(0xFF0096FF), modifier = Modifier.size(24.dp) )
                        }
                        IconButton(onClick = { shareLauncher.launch(sendIntent) }) {
                            Icon(imageVector = Icons.Filled.Share, contentDescription = null,
                                tint = Color(0xFF0096FF), modifier = Modifier.size(24.dp) )
                        }
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null,
                                tint = Color(0xFF0096FF), modifier = Modifier.size(30.dp) )
                        }
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Outlined.Star, contentDescription = null,
                                tint = Color(0xFF0096FF), modifier = Modifier.size(30.dp) )
                        }
                    }
                }
            }
        }
    }
}
