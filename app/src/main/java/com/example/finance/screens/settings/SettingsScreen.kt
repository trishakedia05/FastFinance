package com.example.finance.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.finance.R
import com.example.finance.navigation.allScreens
import com.example.finance.screens.main.MainViewModel

//import com.example.finance.screens.main.top_bar

@Composable
fun SettingsScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){
    top_bar_setting(mainViewModel = mainViewModel,navController)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun top_bar_setting(mainViewModel: MainViewModel,navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier= Modifier.padding(vertical=1.dp),
                navigationIcon = {
                    Row {
                        IconButton(onClick = { navController.navigate(allScreens.MainScreen.name) }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint=  Color(0xFF3F5B60)
                            )
                        }
                        Image(modifier= Modifier
                            .size(53.dp)
                            .padding(horizontal = 5.dp),
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

                    Text("   Flash Finance",modifier= Modifier.padding(horizontal =0.dp),
                        style= TextStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, fontSize = 29.sp)
                    )
                },
                actions = {
                    IconButton(modifier=Modifier.clickable{},onClick = { navController.navigate(allScreens.MainScreen.name) }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Localized description", tint= Color(0xFF3F5B60)
                        )
                    }
                },
                scrollBehavior= TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Text(text = "THIS IS SETTING")
            //show_data(mainViewModel)
        }
    }
}