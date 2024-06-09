package com.yashvant.altbtns

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ZCornerButton(){

    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(0),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        border = BorderStroke(1.dp, Color.Yellow)
    ) {
        Text(text = "ZCornerButton")
    }

}

