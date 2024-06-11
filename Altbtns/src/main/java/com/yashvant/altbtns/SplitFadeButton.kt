package com.yashvant.altbtns

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplitFadeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.large,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isClicked by remember { mutableStateOf(false) }
    val offsetX by animateDpAsState(if (isClicked) 50.dp else 0.dp)
    val opacity by animateFloatAsState(if (isClicked) 0f else 1f)

    Box(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = {
                isClicked = true
                onClick()
            }
        )
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .height(70.dp)
                .width(200.dp),
                /*.offset(x = -offsetX)
                .alpha(opacity),*/
            shape = shape,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green
            )
        ) {
            Text(text = text.substring(0, text.length / 2))
        }
            AnimatedVisibility(visible = isClicked) {
                Divider(
                    modifier = Modifier
                        .align(Alignment.Center)
                        /*.offset(x = offsetX)
                        .alpha(1 - opacity)*/
                    ,
                    thickness = 5.dp,
                    color = Color.White
                )
            }
    }

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(1000)
            isClicked = false
        }
    }
}