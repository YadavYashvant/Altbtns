package com.yashvant.altbtns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var altBtnState by remember { mutableStateOf(AltBtnState.IDLE) }
            val scope = rememberCoroutineScope()

            AltBtn(
                type = AltBtnType.CLOCK,
                width = 200.dp,
                height = 70.dp,
                onClick = {
                    altBtnState = AltBtnState.LOADING
                    scope.launch {
                        delay(2000)
                    }
                    altBtnState = AltBtnState.SUCCESS
                },
                assetColor = Color.Cyan,
                buttonState = altBtnState
            )

        }
    }
}