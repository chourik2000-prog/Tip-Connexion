package com.example.listapp

import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: PasswordVisualTransformation,
){
    OutlinedTextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        label = { Text(text = labelText) },
        leadingIcon = {
            if(leadingIcon != null)
                Icon(imageVector = leadingIcon, null)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = PasswordVisualTransformation(),
        shape = RoundedCornerShape(0)
    )
}


@Preview(showBackground = true)
@Composable
fun PrevTextField(){
    LoginTextField(
        value = "",
        onValueChange = {},
        labelText = "Password",
        visualTransformation = PasswordVisualTransformation()
    )
}