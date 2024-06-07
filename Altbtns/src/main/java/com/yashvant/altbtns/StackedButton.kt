package com.yashvant.altbtns

import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isPressed by remember { mutableStateOf(false) }
    val offsetX by animateDpAsState(if (isPressed) 0.dp else 8.dp, finishedListener = { isPressed = false })
    val offsetY by animateDpAsState(if (isPressed) 0.dp else 8.dp, finishedListener = { isPressed = false })

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
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(btnheight)
                .width(btnwidth),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green
            ),
            border = BorderStroke(2.dp, Color.Yellow),
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
                .height(btnheight)
                .width(btnwidth)
                .offset(x = offsetX, y = offsetY),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow
            )
        ) {
            Text(text = text)
        }
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(1000)
            isPressed = false
        }
    }
}

/*
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
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressAnimationProgress = remember { Animatable(0f) }
    val offsetAnimationProgress = remember { Animatable(0f) }

    Box(
        modifier = modifier
            .width(btnwidth+10.dp)
            .height(btnheight+10.dp)
            .clip(shape)
//            .background(Color(0xFF1E1E1E))
            .clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        OutlinedButton(onClick = onClick,
            shape = RoundedCornerShape(30),
            modifier = Modifier
                .height(btnheight)
                .width(btnwidth)
            ,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow
            ),
            border = BorderStroke(2.dp, Color.White),
        ) {
            Text(text = "")
        }
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(30),
            modifier = Modifier
                .height(btnheight)
                .width(btnwidth)
                .offset(x = 10.dp, y = 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green
            )
//                .size(48.dp)
//                .offset(y = offsetAnimationProgress.value.dp),
        ) {
            Text(text = text)

        }

    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    pressAnimationProgress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                    offsetAnimationProgress.animateTo(
                        targetValue = 10f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
                is PressInteraction.Release -> {
                    pressAnimationProgress.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                    offsetAnimationProgress.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
                is PressInteraction.Cancel -> {
                    pressAnimationProgress.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                    offsetAnimationProgress.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
            }
        }
    }
}*/
