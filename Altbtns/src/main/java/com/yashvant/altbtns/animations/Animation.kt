package com.yashvant.altbtns.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@Composable
fun AltBtnRepeatedFloatAnimation(
    initialValue: Float,
    targetValue: Float,
    durationMillis: Int
): Float {
    val repeatedFloat by rememberInfiniteTransition().animateValue(
        initialValue = initialValue,
        targetValue = targetValue,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    return repeatedFloat
}

@Composable
fun AltBtnRepeatedDpAnimation(initialValue: Dp, targetValue: Dp, durationMillis: Int): Dp {
    val repeatedDp by rememberInfiniteTransition().animateValue(
        initialValue = initialValue,
        targetValue = targetValue,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    return repeatedDp
}

@Composable
fun AltBtnRepeatedAnimation(
    initialValue: Color,
    targetValue: Color,
    durationMillis: Int
): Color {
    val repeatedColor by rememberInfiniteTransition(label = "").animateColor(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    return repeatedColor
}

@Composable
fun sizeAnimationMethod(targetValue: Dp, durationMillis: Int): Dp {
    val size by animateDpAsState(
        targetValue = targetValue,
        tween(
            durationMillis = durationMillis,
            easing = LinearOutSlowInEasing
        ), label = ""
    )
    return size
}

@Composable
fun altBtnAnimateIntAsState(targetValue: Int, durationMillis: Int): Int {
    val newValue by animateIntAsState(targetValue, animationSpec = tween(durationMillis),
        label = ""
    )
    return newValue
}

@Composable
fun altBtnAnimateFloatAsState(targetValue: Float, durationMillis: Int): Float {
    val newValue by animateFloatAsState(targetValue, animationSpec = tween(durationMillis),
        label = ""
    )
    return newValue
}
