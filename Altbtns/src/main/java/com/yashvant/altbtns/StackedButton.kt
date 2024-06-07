package com.yashvant.altbtns

import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StackedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.large,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressAnimationProgress = remember { Animatable(0f) }
    val offsetAnimationProgress = remember { Animatable(0f) }

    Button(
        onClick = onClick,
        modifier = modifier
//            .size(52.dp)
            .width(200.dp)
            .height(70.dp)
            .offset(x = 4.dp, y = 4.dp),
        shape = shape,
        enabled = enabled
    ) {}

    Button(
        onClick = onClick,
        modifier = modifier
//            .size(48.dp)
            .width(200.dp)
            .height(70.dp)
            .offset(y = offsetAnimationProgress.value.dp),
        shape = shape,
        enabled = enabled
    ) {
        content()
    }

    // Animate the button press
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
}