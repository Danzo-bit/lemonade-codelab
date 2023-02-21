package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp(){
    //Current Number of taps required
    var numOfTaps by remember { mutableStateOf(2) }
    // number of taps to be updated when  user taps image
    var taps = 0
    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        when (currentStep) {
            1 -> LemonTextAndImage(instruction = stringResource(R.string.lemon_tree_desc),
                drawable = R.drawable.lemon_tree,
                onClick = {
                    currentStep = 2
                    //Updates number of tap required
                    numOfTaps =(2..4).random()
                })
            2 -> LemonTextAndImage(instruction = stringResource(R.string.lemon_desc),
                drawable = R.drawable.lemon_squeeze,
                onClick = {
                    //updates tap when user clicks image
                    taps++
                    if(taps == numOfTaps){
                        //reset tap
                        taps = 0
                        //change screen
                        currentStep = 3
                    }
                })

            3 -> LemonTextAndImage(instruction = stringResource(R.string.drink_lemonade_desc),
                drawable = R.drawable.lemon_drink, onClick = {currentStep = 4})

            4 -> LemonTextAndImage(instruction = stringResource(R.string.empty_glass_desc),
                drawable = R.drawable.lemon_restart, onClick = {currentStep = 1})

        }
    }
}

@Composable
fun LemonTextAndImage(instruction: String, drawable: Int, onClick: () -> Unit){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = instruction, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(drawable),
            contentDescription = stringResource(R.string.lemon_tree),
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onClick()
                }.border(
                    width = 2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    shape = RoundedCornerShape(5.dp)
                )
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}