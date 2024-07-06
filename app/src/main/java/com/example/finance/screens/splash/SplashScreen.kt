package com.example.finance.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finance.R
import com.example.finance.navigation.allScreens
import kotlinx.coroutines.delay

@Composable
fun My_SplashScreen(navController: NavController){
    val scale= remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f,
    animationSpec = tween(
        durationMillis = 800,
        easing={
            OvershootInterpolator(8f).getInterpolation(it)
        }
    ))
        delay(1000L)
        navController.navigate(allScreens.MainScreen.name)
    })

    Surface(modifier= Modifier.fillMaxSize(),
        color=Color(0xff0A0808)
        ) {
        Box(contentAlignment = Alignment.Center){
            Column(modifier=Modifier.padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Image(modifier= Modifier
                    .size(200.dp)
                    .padding(horizontal = 5.dp),
                    painter = painterResource(id = R.drawable.image_1),
                    contentDescription = null
                )
                Text("  Flash Finance",color= Color.Gray,modifier=Modifier.padding(horizontal =6.dp),
                    style= TextStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                )
            }
        }

    }
}

