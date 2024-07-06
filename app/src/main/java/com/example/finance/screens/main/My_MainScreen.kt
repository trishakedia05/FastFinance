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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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


@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){
    top_bar(mainViewModel = mainViewModel, navController =navController )
    show_data(navController = navController,mainViewModel=mainViewModel)

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun top_bar(mainViewModel: MainViewModel, navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier= Modifier.padding(vertical=1.dp),
                navigationIcon = {
                    Row {
                        IconButton(onClick = { navController.navigate(allScreens.SplashScreen.name) }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint= Color(0xFF3F5B60)
                            )
                        }
                        Image(modifier= Modifier
                            .size(53.dp)
                            .padding(horizontal = 3.dp),
                            painter = painterResource(id = R.drawable.app_logo), // Replace with your image resource
                            contentDescription = null // Provide a meaningful content description
                        )
                    }

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFDDB87D),
                    titleContentColor = Color(0xFF3F5B60)
                    // titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {

                    Text("   Flash Finance",modifier= Modifier.padding(horizontal =2.dp),
                        style= TextStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 24.sp,
                            textAlign = TextAlign.Center)
                    )
                },
                actions = {
                    IconButton(modifier=Modifier.padding(horizontal=0.dp),onClick = { navController.navigate(allScreens.SettingScreen.name) }) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Localized description", tint= Color(0xFF3F5B60)

                        )
                    }
                },
                scrollBehavior= TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            )
        },
        bottomBar =   {
            BottomAppBar(modifier = Modifier.padding(2.dp),
                containerColor = Color.White,
                contentColor = Color.Black,
                tonalElevation = 4.dp) {
                Row() {
                    IconButton(onClick = { navController.navigate(allScreens.SplashScreen.name) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color(0xFF3F5B60)
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Localized description",
                            tint = Color.Black,

                            )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Localized description",
                            tint = Color.Black)
                    }

                }
            }}
    )
     {innerPadding->
        Box(modifier = Modifier.padding(innerPadding)){
         show_data(navController,mainViewModel)
        //    show_gpt_data(navController = navController)

        }
    }
}



@Composable
fun show_data(navController:NavController, mainViewModel: MainViewModel = hiltViewModel())
{
    //coder
    val mydata= produceState<DataOrException<NewsStatus, Boolean, Exception>>(initialValue =
        DataOrException(loading=true) ) {
        value = mainViewModel.getAllNews("in", "business", constants.apiKey)
    }.value
    if(mydata.loading==true){
    Surface(modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(10.dp)) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Loading News..")
            Spacer(modifier=Modifier.size(2.dp))
            CircularProgressIndicator(modifier = Modifier.size(40.dp),color = Color.White)
        }

    }
}   else if(mydata.data!=null){
    Log.d("mydata","${mydata.data}")
    App_Layout(data_to_show=mydata.data!!,navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun App_Layout(data_to_show: NewsStatus, navController: NavController) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    Surface(modifier = Modifier, color = Color.LightGray)
    {
        LazyRow(
            modifier = Modifier
                .padding(5.dp)

                , contentPadding = PaddingValues(horizontal = 1.dp, vertical = 5.dp),
            state = lazyListState, flingBehavior = snapBehavior
        ) {
            items(items = data_to_show.articles) { item: Article ->

                    Box(modifier= Modifier
                        .border(
                            BorderStroke(3.dp, Color.Black),
                            RoundedCornerShape(size = 18.dp)
                        )
                        .clip(shape = RoundedCornerShape(18.dp))
                        .fillMaxHeight()
                        .width(350.dp)
                        , propagateMinConstraints = true
                    ){
                        structure(item = item,navController=navController)
                     //   Spacer(modifier = Modifier.height(10.dp))

                    }
            }
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun structure(item:Article,navController: NavController) {

    val sendIntent = Intent().apply {
        action = ACTION_SEND
        putExtra(EXTRA_TEXT, item.url)
        type = "text/plain"
    }
    val context = LocalContext.current
    val shareLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                // Handle the result if needed
                Toast.makeText(context, "Shared successfully", Toast.LENGTH_SHORT).show()
            }
        }
    if (item.urlToImage != null && item.description != null && item != null) {
        Surface(modifier = Modifier.fillMaxHeight(), color = Color.White)
        {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            )
            {

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp), color = Color.White
                ) {

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
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(2.dp, Color.LightGray),
                            RoundedCornerShape(size = 10.dp)
                        )
                        .padding(3.dp)
                )
                {
                    Column() {
                        Spacer(modifier = Modifier.size(3.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 13.dp, vertical = 3.dp),
                            text = item.title.substringBeforeLast("-").substringBeforeLast("|"),
                            color = Color.Black,
                            fontWeight = FontWeight(1000),
                            fontFamily = FontFamily(
                                Font(
                                    googleFont = GoogleFont("PT Serif"),
                                    fontProvider = provider
                                )
                            ),
                            fontSize = 20.sp
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth() // Occupy the full width of the parent container
                                .padding(horizontal = 13.dp, vertical = 1.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = DateFormatted(item.publishedAt.substringBefore("T")),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        googleFont = GoogleFont("PT Serif"),
                                        fontProvider = provider
                                    )
                                ),
                                textAlign = TextAlign.Right
                            )
                        }
                        Spacer(modifier = Modifier.size(5.dp))
                        Divider(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            thickness = 2.dp,
                            color = Color.LightGray
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                            text = item.description + ".",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(
                                    googleFont = GoogleFont("Crimson Text"),
                                    fontProvider = provider
                                )
                            ),
                            textAlign = TextAlign.Start
                        )


                        // Divider(modifier=Modifier.padding(horizontal = 6.dp),thickness = 2.dp, color = Color.LightGray)
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
                                        "following significance in the share market.This is not a financial advice, " +
                                        "but is my inference of the news.",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        //    Divider(modifier=Modifier.padding(horizontal = 5.dp),thickness = 2.dp, color = Color.LightGray)


                    }


                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .border(
                            BorderStroke(1.dp, Color.Gray),
                            shape = RectangleShape
                        ), color = Color.Black
                ) {
                    Column(modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxHeight())
                    {

                        Row() {
//                            IconButton(onClick = { }) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.home_colored),
//                                    contentDescription = null // decorative element
//                                )
//                            }
//                            IconButton(onClick = { }) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.home_colored),
//                                    contentDescription = null // decorative element
//                                )
//                            }
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.home_colored),
                                    contentDescription = null // decorative element
                                )
                            }
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search__filled),
                                    contentDescription = null // decorative element
                                )
                            }
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search_outline),
                                    contentDescription = null // decorative element
                                )
                            }
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.settings_filled),
                                    contentDescription = null // decorative element
                                )
                            }
                            IconButton(onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.trophy),
                                    contentDescription = null // decorative element
                                )
                            }
                            IconButton(onClick = { }) {
                            }
                        }
                    }
                }
                //   }


            }
        }
    } else {
    }
}








