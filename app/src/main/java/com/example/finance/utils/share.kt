package com.example.finance.utils

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShareIconButton(activity: ComponentActivity, text: String) {
    val context = LocalContext.current
    val shareLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                // Handle the result if needed
                Toast.makeText(context, "Shared successfully", Toast.LENGTH_SHORT).show()
            }
        }

            }
