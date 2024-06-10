package com.yashvant.altbtns

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplitFadeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isClicked by remember { mutableStateOf(false) }
    val offsetX by animateDpAsState(if (isClicked) 50.dp else 0.dp)
    val opacity by animateFloatAsState(if (isClicked) 0f else 1f)

    Row(
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
                .weight(1f)
                .offset(x = -offsetX)
                .alpha(opacity),
            shape = shape,
            enabled = enabled
        ) {
            Text(text = text.substring(0, text.length / 2))
        }

        Button(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .offset(x = offsetX)
                .alpha(opacity),
            shape = shape,
            enabled = enabled
        ) {
            Text(text = text.substring(text.length / 2))
        }
    }

    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(1000)
            isClicked = false
        }
    }
}