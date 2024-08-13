package com.example.limonada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.limonada.ui.theme.LimonadaTheme
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimonadaTheme {
                LimonadaApp()
            }
        }
    }
}

@Preview
@Composable
fun LimonadaApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    when (currentStep) {
        1 -> {
            TreeButtonAndImage()
            currentStep = 2
            squeezeCount = (2..4).random()
        }
        2 -> {
            LemonButtonAndImage(onImageClick = {
                squeezeCount--
                if (squeezeCount == 0) {
                    currentStep = 3
                }
            })
        }
        3 -> {
            GlassOfLemonadeButtonAndImage()
            currentStep = 4
        }
        4 -> {
            EmptyGlassButtonAndImage()
            currentStep = 1
        }
    }
}

@Composable
fun TreeButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_squeeze
        else -> R.drawable.lemon_tree
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(R.string.Lemon_tree)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1)}) {
            Text(stringResource(R.string.Lemon_tree))
        }
    }
    Modifier.fillMaxSize()
        .wrapContentSize(Alignment.Center)
}

@Composable
fun LemonButtonAndImage(modifier: Modifier = Modifier, onImageClick: () -> Unit,) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_squeeze
        4 -> R.drawable.lemon_squeeze
        5 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_squeeze
    }
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(R.string.Lemon_tree)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick= onImageClick)   {
            Text(stringResource(R.string.Lemon_tree))
        }
    }
    Modifier.fillMaxSize()
        .wrapContentSize(Alignment.Center)
}

@Composable
fun GlassOfLemonadeButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_drink
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(R.string.Glass_of_lemonade)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1)}) {
            Text(stringResource(R.string.Glass_of_lemonade))
        }
    }
    Modifier.fillMaxSize()
        .wrapContentSize(Alignment.Center)
}

@Composable
fun EmptyGlassButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        else -> R.drawable.lemon_restart
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(R.string.Empty_glass)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1)}) {
            Text(stringResource(R.string.Empty_glass))
        }
    }
    Modifier.fillMaxSize()
        .wrapContentSize(Alignment.Center)
}