package com.yashvant.altbtns

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun StackedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    btnwidth: Dp = 200.dp,
    btnheight: Dp = 70.dp,
    shape: Shape = MaterialTheme.shapes.large,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    fgColor: Color = Color.Green,
    bgColor: Color = Color.Yellow,
    fontsize: TextUnit = 18.sp,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isPressed by remember { mutableStateOf(false) }
    val offsetX by animateDpAsState(if (isPressed) 0.dp else 8.dp, finishedListener = { isPressed = false })
    val offsetY by animateDpAsState(if (isPressed) 0.dp else 8.dp, finishedListener = { isPressed = false })

    val poppinsfamily = FontFamily(
        Font(R.font.poppins),
    )

    Box(
        modifier = modifier
            .width(btnwidth + 8.dp)
            .height(btnheight + 8.dp)
            .clip(shape)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        Surface(
//            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxSize(),
                /*.height(btnheight)
                .width(btnwidth),*/
            color = bgColor,

            border = BorderStroke(2.dp, fgColor),
        ) {
            Text(text = "")
        }
        Button(
            onClick = {
                onClick
                isPressed = true
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxSize()
                /*.height(btnheight)
                .width(btnwidth)*/
                .offset(x = offsetX, y = offsetY),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = fgColor
            )
        ) {
            Text(
                text = text,
                fontFamily = poppinsfamily,
                fontWeight = FontWeight.Bold,
                fontSize = fontsize,
            )
        }
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(1000)
            isPressed = false
        }
    }
}
