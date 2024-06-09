package com.yashvant.altbtns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var altBtnState by remember { mutableStateOf(AltBtnState.IDLE) }
            val scope = rememberCoroutineScope()
//            val scrollable = rememberScrollState()

            Box(
                modifier = Modifier.fillMaxSize(),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
//                        .verticalScroll(scrollable)
                        .padding(20.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
//                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {
                    AltBtn(
                        type = AltBtnType.CLOCK,
                        width = 200.dp,
                        height = 70.dp,
                        cornerRadius = 32,
                        onClick = {
                            altBtnState = AltBtnState.LOADING
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Green,
                            contentColor = Color.Yellow
                        ),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        assetColor = Color.Black,
                        buttonState = altBtnState,
                        text = "Welcome to AltBtns",
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    StackedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth(),
                        text = "Stacked Button",
                    )

//                   AnimatingBox()
//                    BallCanvas(name = "Welcome to AltBtns")
//                    SpinSphere(ringColor = Color.Yellow, circleColor = Color.Green)
//                    RotateSphere(ringColor = Color.Green, circleColor = Color.Yellow)
                    LaunchedEffect(key1 = altBtnState) {
                        when (altBtnState) {
                            AltBtnState.LOADING -> {
                                delay(3000)
                                altBtnState = AltBtnState.SUCCESS
                            }
                            AltBtnState.SUCCESS -> {
                                delay(3000)
                                altBtnState = AltBtnState.IDLE
                            }

                            AltBtnState.IDLE -> println()
                            AltBtnState.FAILURE -> println()
                        }

                    }

                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewAltBtn() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(0)
    ) {
        Text(text = "ZCornerButton")
    }
}