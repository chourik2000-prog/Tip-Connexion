package com.example.listapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listapp.ui.theme.ListAppTheme
//val defaultPadding = 16.dp
//val itemSpacing = 8.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListAppTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .paint(
                            painterResource(id = R.drawable.bg),
                            contentScale = ContentScale.FillBounds
                        )

                ) {
                    val navController = rememberNavController()
                    com.example.listapp.NavGraph(navController)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess:() -> Unit){
    var username by remember{ mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var context = LocalContext.current.applicationContext

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 26.dp, vertical = 120.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
//        Texte de connexion
        Text(
            text = "Connexion",
            color = Color.White,
            fontSize = 46.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(16.dp))

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 26.dp, vertical = 280.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
//        Champ de username
        OutlinedTextField(value = username, onValueChange = {username = it},
            label = { Text(text = "username", color = Color.Blue) },
            shape = RoundedCornerShape(0.dp),
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = Color.Blue,
                unfocusedLeadingIconColor = Color.Blue,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Blue,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Blue,
                unfocusedPlaceholderColor = Color.Blue
            ), leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

//Champ de password
        OutlinedTextField(value = password, onValueChange = {password = it},
            label = { Text(text = "Password", color = Color.Blue) },
            shape = RoundedCornerShape(0.dp),
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = Color.Blue,
                unfocusedLeadingIconColor = Color.Blue,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Blue,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Blue,
                unfocusedPlaceholderColor = Color.Blue
            ), leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
//      Champ du bouton
        Button(onClick = {
            if (authenticate(username, password)){
                onLoginSuccess()
                Toast.makeText(context, "LoginSuccessful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }, colors = ButtonDefaults.buttonColors(Color.White),
            contentPadding = PaddingValues(start = 60.dp, end = 60.dp, top = 8.dp, bottom = 8.dp),
            modifier = Modifier
                .height(60.dp)
                .padding(top = 10.dp),
            shape = RoundedCornerShape(0.dp)
            ) {
              Text(
                  text = "Connexion",
                  color = Color.Blue,
                  fontWeight = FontWeight.Bold
              )
        }

    }

}

private fun authenticate(username: String, password: String): Boolean{
    val validUsername = "admin"
    val validPassword = "admin123"
    return username == validUsername && password == validPassword
}

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginScreen(onLoginSuccess = {
                navController.navigate("home"){
                    popUpTo(0)
                }
            })
        }
        composable("home"){
            HomeScreen()
        }
    }
}
