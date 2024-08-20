package com.example.lemonadelocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadelocal.ui.theme.LemonadeLocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeLocalTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    TreeButtonAndImage {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
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
                    GlassOfLemonadeButtonAndImage {
                        currentStep = 4
                    }
                }
                4 -> {
                    EmptyGlassButtonAndImage {
                        currentStep = 1
                    }
                }
            }
        }
    }
}


@Composable
fun TreeButtonAndImage(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_tree),
            contentDescription = stringResource(R.string.Lemon_tree)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.Lemon_tree))
        }
    }
}


@Composable
fun LemonButtonAndImage(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_squeeze),
            contentDescription = stringResource(R.string.Lemon_tree)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.Lemon_tree))
        }
    }
}

@Composable
fun GlassOfLemonadeButtonAndImage(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_drink),
            contentDescription = stringResource(R.string.Glass_of_lemonade)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.Glass_of_lemonade))
        }
    }
}

@Composable
fun EmptyGlassButtonAndImage(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_restart),
            contentDescription = stringResource(R.string.Empty_glass)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.Empty_glass))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonPreview() {
    LemonadeLocalTheme {
        LemonadeApp()
    }
}
