package com.yashvant.altbtns

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yashvant.altbtns.utils.HOUR_DURATION
import com.yashvant.altbtns.utils.MINUTE_DURATION
import com.yashvant.altbtns.utils.oneFloat
import com.yashvant.altbtns.utils.six
import com.yashvant.altbtns.utils.ten
import com.yashvant.altbtns.utils.threeSixtyFloat
import com.yashvant.altbtns.utils.twelve
import com.yashvant.altbtns.utils.two
import com.yashvant.altbtns.utils.zeroFloat

@Composable
fun ClockLoadingBar(
    modifier: Modifier = Modifier,
    minuteColor: Color,
    hourColor: Color,
    minHeightWidth: Dp
) {
    val progressRotation = AltBtnRepeatedFloatAnimation(
        initialValue = zeroFloat,
        targetValue = oneFloat,
        durationMillis = MINUTE_DURATION
    )
    val progressHourRotation = AltBtnRepeatedFloatAnimation(
        initialValue = zeroFloat,
        targetValue = oneFloat,
        durationMillis = HOUR_DURATION
    )
    Canvas(modifier = modifier) {
        val middle = Offset(size.minDimension / two, size.minDimension / two)
        withTransform(
            {
                rotate(threeSixtyFloat * progressRotation, middle)
            }, {
                drawLine(
                    strokeWidth = six.dp.toPx(),
                    cap = StrokeCap.Round,
                    color = minuteColor,
                    start = middle,
                    end = Offset(
                        size.minDimension / two,
                        (minHeightWidth / two - ten.dp).toPx()
                    )
                )
            }
        )
        withTransform(
            {
                rotate(threeSixtyFloat * progressHourRotation, middle)
            }, {
                drawLine(
                    strokeWidth = six.dp.toPx(),
                    cap = StrokeCap.Round,
                    color = hourColor,
                    start = middle,
                    end = Offset(
                        size.minDimension / two,
                        (minHeightWidth / two - twelve.dp).toPx()
                    )
                )
            }
        )
    }
}