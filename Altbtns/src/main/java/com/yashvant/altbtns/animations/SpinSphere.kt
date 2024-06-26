package com.yashvant.altbtns.animations

import android.view.MotionEvent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yashvant.altbtns.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SpinSphere(
    modifier: Modifier = Modifier,
    ringColor: Color,
    circleColor: Color,
) {

    // button state
    val selected = remember { mutableStateOf(false) }
    val circleScale = animateFloatAsState(if (selected.value) 0.8f else 1.2f, label = "")
    val iconScale = animateFloatAsState(if (selected.value) 1f else 1.5f, label = "")
    val speed = remember { mutableIntStateOf(100) }


    // Ring rotate animation
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colors.onPrimary,
        targetValue = MaterialTheme.colors.onSecondary,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
        ), label = ""
    )

    val rotateRing by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
        ), label = ""
    )

    // Ring grow animation
    val animateFloat = remember { Animatable(0f) }
    LaunchedEffect(animateFloat) {
        animateFloat.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
        )
    }

    Box(modifier = modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
        Box(modifier = modifier
            .scale(circleScale.value)
            .size(100.dp)
            .drawBehind {

                val canvasSize = size
                val canvasWidth = size.width
                val canvasHeight = size.height

                withTransform({
                    rotate(degrees = rotateRing)
                }) {
                    drawArc(
                        color = color,
                        startAngle = 0f,
                        sweepAngle = 60f * animateFloat.value,
                        useCenter = true,
                        size = canvasSize / 1f,
                        style = Stroke(100f, cap = StrokeCap.Round, join = StrokeJoin.Round),

                        )

                    drawArc(
                        color = color,
                        startAngle = 90f,
                        sweepAngle = 60f * animateFloat.value,
                        useCenter = true,
                        size = canvasSize / 1f,
                        style = Stroke(100f, cap = StrokeCap.Round, join = StrokeJoin.Round),
                    )

                    drawArc(
                        color = color,
                        startAngle = 180f,
                        sweepAngle = 60f * animateFloat.value,
                        useCenter = true,
                        size = canvasSize / 1f,
                        style = Stroke(100f, cap = StrokeCap.Round, join = StrokeJoin.Round),

                        )

                    drawArc(
                        color = color,
                        startAngle = 270f,
                        sweepAngle = 60f * animateFloat.value,
                        useCenter = true,
                        size = canvasSize / 1f,
                        style = Stroke(100f, cap = StrokeCap.Round, join = StrokeJoin.Round),
                    )
                }
            }
            .clip(RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center
        ) {

            IconButton(onClick = {
                speed.intValue = 1000
            },
                modifier = modifier
                    .scale(iconScale.value)
                    .fillMaxSize()
                    .background(circleColor)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected.value = false
                            }
                        }
                        true
                    }) {

                Icon(
                    painter = painterResource(id = R.drawable.fieries),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp, 64.dp),
                    tint = color
                )
            }
        }
    }
}
