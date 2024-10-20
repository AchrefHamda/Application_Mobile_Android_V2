package com.example.application_mobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Acceuil() {
    val context = LocalContext.current
    val (savedUsername, _) = getLoginInfo(context)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Welcome Back  !!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "This is the home page you Welcome Mr ${savedUsername ?: "User"} !!! ")
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.homep),
            contentDescription = "Forget Password Image",
            modifier = Modifier.size(400.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                // Clear login information
                val sharedPreferences: SharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()

                // Navigate back to the login page
                val intent = Intent(context, LoginP::class.java)
                context.startActivity(intent)
                (context as Activity).finish() // Finish current activity
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFECA52B), // Gold color
                contentColor = Color.White,
                // Text color
            )
        ) {
            Text(text = "Logout", style = TextStyle(fontWeight = FontWeight.Bold),)
        }

    }
}

class AcceuilP : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve saved login information
        val (savedUsername, savedPassword) = getLoginInfo(this)

        // Check if the user is already logged in
        if (!savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
            // Launch the home page if already logged in
            setContent {
                Acceuil() // Display the home UI
            }
        } else {
            setContent {
                Login() // Display the login UI
            }
        }
    }
}

