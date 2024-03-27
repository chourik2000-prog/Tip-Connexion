package com.example.listapp

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listapp.ui.theme.ListAppTheme

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(){
    val (userName, setUsername) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (checked, onCheckChange) = rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val imageLogo = painterResource(R.drawable.bg)
//    Affichage du logo
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 70.dp,
                start = 17.dp,
                end = 17.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = imageLogo, contentDescription = "logo de l'application")
    }
//    Texte de connexion
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 160.dp,
                start = 17.dp,
                end = 17.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HeaderText(
            text = "Connexion",
            modifier = Modifier
                .padding(
                    start = 17.dp,
                    end = 17.dp,
                    bottom = 10.dp
                )
                .align(alignment = Alignment.Start)
        )
//Username
        LoginTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = setUsername,
            labelText = "Email",
            leadingIcon = Icons.Default.Person,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(itemSpacing))
//password
        LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Mot de passe",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(Modifier.height(itemSpacing))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = onCheckChange)
                Text(text = "Se souvenir de moi")
            }
            TextButton(onClick = { }) {
                Text(text = "Mot de passe oublié?")
            }
        }
        Spacer(Modifier.height(itemSpacing))
//Boutton
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 30.dp
                )
                .height(60.dp),
            shape = RoundedCornerShape(0.dp),
            colors = with(ButtonDefaults) { buttonColors(Color.Red) },
        ) {
            Text(
                text = "Connexion",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        AlternativeLoginOptions(
            onIconClick = {index ->
                when(index){
                    0->{
                        Toast.makeText(context, "Vous avez choisi une connexion par GitHub", Toast.LENGTH_SHORT).show()
                    }
                    1->{
                        Toast.makeText(context, "Vous avez choisi une connexion par Facebook", Toast.LENGTH_SHORT).show()
                    }
                    2->{
                        Toast.makeText(context, "Vous avez choisi une connexion par Google", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onSignUpClick = {},
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        )
    }
}

@Composable
//fonction de conexion alternative
fun AlternativeLoginOptions(
    onIconClick:(index:Int) -> Unit,
    onSignUpClick:() -> Unit,
    modifier: Modifier = Modifier
){
    val iconList = listOf(
        R.drawable.facebook,
        R.drawable.google
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Se connecter avec")
//        Ligne de autre moyen de connexion
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly){
            iconList.forEachIndexed { index, iconResId ->
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = "Autre moyen de connexion",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            onIconClick(index)
                        }
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(defaultPadding))
            }
        }
//        Ligne de je n'ai pas de compte
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Vous n'avez pas de compte?")
            Spacer(Modifier.height(itemSpacing))
            TextButton(onClick = onSignUpClick) {
                Text(text = "Créer un compte")

            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen(){
    ListAppTheme {
        LoginScreen()
    }
}

