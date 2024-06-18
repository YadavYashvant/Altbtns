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
fun ZCornerButton(modifier: Modifier = Modifier,
                  btnText: String,
                  onClick: () -> Unit,
                  borderColor: Color = Color.Yellow,
) {
    Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(0),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            border = BorderStroke(3.dp, borderColor),
            modifier = modifier
    ) {
        Text(text = btnText)
    }
}
