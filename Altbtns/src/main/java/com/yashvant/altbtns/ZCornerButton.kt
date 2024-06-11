package com.yashvant.altbtns

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ZCornerButton(
    modifier: Modifier = Modifier,
    btnText: String,
){

    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(0),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        border = BorderStroke(3.dp, Color.Yellow),
        modifier = modifier
    ) {
        Text(text = btnText)
    }

}

