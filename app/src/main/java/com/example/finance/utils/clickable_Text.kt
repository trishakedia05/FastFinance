package com.example.finance.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance.model.Article

@Composable
fun ClickableTextWithLink(text: String, link: String,item: Article) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    Row(modifier = Modifier
        .fillMaxWidth() // Occupy the full width of the parent container
        .padding(horizontal = 13.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically){
        ClickableText(text = AnnotatedString(text), onClick ={uriHandler.openUri(link) },
            modifier = Modifier, softWrap = true, style = TextStyle(color= Color.White, fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,  fontSize = 18.sp)
            //textDecoration = TextDecoration.Underline,
        )
    }
}