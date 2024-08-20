package com.example.limonada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.limonada.ui.theme.LimonadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimonadaTheme {
                LemonadeApp() // inicia la app de limonada
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) } // controla el paso actual
    var squeezeCount by remember { mutableStateOf(0) } // cuenta los apretones necesarios

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade", // título de la app
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer // color del top bar
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), // relleno para mantener la UI ordenada
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    LemonTree(onImageClick = {
                        currentStep = 2 // cambia al paso de apretar el limón
                        squeezeCount = (2..4).random() // determina los apretones requeridos
                    })
                }
                2 -> {
                    Lemon(onImageClick = {
                        squeezeCount-- // disminuye el contador de apretones
                        if (squeezeCount == 0) {
                            currentStep = 3 // avanza al siguiente paso
                        }
                    })
                }
                3 -> {
                    GlassOfLemonade(onImageClick = {
                        currentStep = 4 // avanza al paso de vaso vacío
                    })
                }
                4 -> {
                    EmptyGlass(onImageClick = {
                        currentStep = 1 // reinicia al primer paso
                    })
                }
            }
        }
    }
}

@Composable
fun LemonTree(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center), // centra el contenido
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_tree), // imagen del árbol de limón
            contentDescription = stringResource(R.string.Lemon_tree),
            modifier = Modifier.wrapContentSize()
        )
        Spacer(modifier = Modifier.height(16.dp)) // espacio entre la imagen y el botón
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.Lemon_tree))
        }
    }
}

@Composable
fun Lemon(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center), // centra el contenido
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_squeeze), // imagen de apretar el limón
            contentDescription = stringResource(R.string.lemon_select),
            modifier = Modifier.wrapContentSize()
        )
        Spacer(modifier = Modifier.height(16.dp)) // espacio entre la imagen y el botón
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.lemon_select))
        }
    }
}

@Composable
fun GlassOfLemonade(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center), // centra el contenido
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_drink), // imagen del vaso de limonada
            contentDescription = stringResource(R.string.Glass_of_lemonade),
            modifier = Modifier.wrapContentSize()
        )
        Spacer(modifier = Modifier.height(16.dp)) // espacio entre la imagen y el botón
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.Glass_of_lemonade))
        }
    }
}

@Composable
fun EmptyGlass(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center), // centra el contenido
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_restart), // imagen del vaso vacío
            contentDescription = stringResource(R.string.Empty_glass),
            modifier = Modifier.wrapContentSize()
        )
        Spacer(modifier = Modifier.height(16.dp)) // espacio entre la imagen y el botón
        Button(onClick = onImageClick) {
            Text(stringResource(R.string.Empty_glass))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonPreview() {
    LimonadaTheme {
        LemonadeApp() // previsualiza la app de limonada
    }
}
