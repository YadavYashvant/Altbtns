package com.yashvant.altbtns

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yashvant.altbtns.utils.ZERO
import com.yashvant.altbtns.utils.ten
import com.yashvant.altbtns.utils.three
import com.yashvant.altbtns.utils.threeSixtyFloat
import com.yashvant.altbtns.utils.zeroFloat

@Composable
fun PrintLoadingBar(
    type: AltBtnType,
    progressAlpha: Float,
    assetColor: Color,
    minHeightWidth: Dp,
    durationMillis: Int,
    hourHandColor: Color,
    customLoadingIconPainter: Painter,
    customLoadingEffect: AltBtnCustomLoading,
    customLoadingPadding: Int = ZERO
) {
    when (type) {
        AltBtnType.CIRCLE -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .graphicsLayer(alpha = progressAlpha)
                    .size(minHeightWidth - ten.dp),
                color = assetColor,
                strokeWidth = three.dp
            )
        }
        AltBtnType.WHEEL -> {
            Icon(
                painter = painterResource(id = R.drawable.wheel),
                contentDescription = null,
                modifier = Modifier
                    .size(minHeightWidth - ten.dp)
                    .graphicsLayer { alpha = progressAlpha }
                    .rotate(
                        AltBtnRepeatedFloatAnimation(
                            zeroFloat,
                            threeSixtyFloat,
                            durationMillis
                        )
                    ),
                tint = assetColor
            )
        }
        AltBtnType.ZOOM_IN_OUT_CIRCLE -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .graphicsLayer(alpha = progressAlpha)
                    .size(
                        AltBtnRepeatedDpAnimation(
                            initialValue = minHeightWidth - ten.dp,
                            targetValue = ten.dp,
                            durationMillis = durationMillis
                        )
                    ),
                color = assetColor,
                strokeWidth = three.dp
            )
        }
        AltBtnType.CLOCK -> {
            ClockLoadingBar(
                modifier = Modifier
                    .graphicsLayer { alpha = progressAlpha },
                minuteColor = assetColor,
                minHeightWidth = minHeightWidth,
                hourColor = hourHandColor
            )
        }
        AltBtnType.SPIRAL -> {
            Icon(
                painter = painterResource(id = R.drawable.spiral),
                contentDescription = null,
                modifier = Modifier
                    .size(minHeightWidth - ten.dp)
                    .graphicsLayer { alpha = progressAlpha }
                    .rotate(
                        AltBtnRepeatedFloatAnimation(
                            initialValue = threeSixtyFloat,
                            targetValue = zeroFloat,
                            durationMillis = durationMillis
                        )
                    ),
                tint = assetColor
            )
        }
        AltBtnType.CUSTOM -> {
            val customColor = AltBtnRepeatedAnimation(
                assetColor, if (customLoadingEffect.colorChanger) {
                    Color.White
                } else {
                    assetColor
                }, durationMillis
            )
            val customRotation = AltBtnRepeatedFloatAnimation(
                initialValue = if (customLoadingEffect.rotation) {
                    threeSixtyFloat
                } else {
                    zeroFloat
                },
                targetValue = zeroFloat,
                durationMillis = durationMillis
            )

            val customSize = AltBtnRepeatedDpAnimation(
                initialValue = minHeightWidth - customLoadingPadding.dp,
                targetValue = if (customLoadingEffect.zoomInOut) {
                    ten.dp
                } else {
                    minHeightWidth - customLoadingPadding.dp
                },
                durationMillis = durationMillis
            )
            Icon(
                painter = customLoadingIconPainter,
                contentDescription = null,
                modifier = Modifier
                    .size(customSize)
                    .graphicsLayer { alpha = progressAlpha }
                    .rotate(customRotation),
                tint = customColor
            )
        }

    }
}