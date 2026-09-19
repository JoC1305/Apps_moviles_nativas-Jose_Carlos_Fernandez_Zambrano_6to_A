package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme() {
                ArtSpaceApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceApp() {

    var currentStep by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Galería",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFFFFF)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFF0A1128)
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    ArtCard(
                        artNameResourceId = R.string.art1_name,
                        drawableResourceId = R.drawable.img1,
                        artDescriptionResourceId = R.string.art1_description,
                        artAuthorResourceId = R.string.art1_author,
                        artYearResourceId = R.string.art1_year,
                        onPreviousClick = {
                            currentStep = 5
                        },
                        onNextClick = {
                            currentStep = 2
                        }
                    )
                }
                2 -> {
                    ArtCard(
                        artNameResourceId = R.string.art2_name,
                        drawableResourceId = R.drawable.img2,
                        artDescriptionResourceId = R.string.art2_description,
                        artAuthorResourceId = R.string.art2_author,
                        artYearResourceId = R.string.art2_year,
                        onPreviousClick = {
                            currentStep = 1
                        },
                        onNextClick = {
                            currentStep = 3
                        }
                    )
                }
                3 -> {
                    ArtCard(
                        artNameResourceId = R.string.art3_name,
                        drawableResourceId = R.drawable.img3,
                        artDescriptionResourceId = R.string.art3_description,
                        artAuthorResourceId = R.string.art3_author,
                        artYearResourceId = R.string.art3_year,
                        onPreviousClick = {
                            currentStep = 2
                        },
                        onNextClick = {
                            currentStep = 4
                        }
                    )
                }
                4 -> {
                    ArtCard(
                        artNameResourceId = R.string.art4_name,
                        drawableResourceId = R.drawable.img4,
                        artDescriptionResourceId = R.string.art4_description,
                        artAuthorResourceId = R.string.art4_author,
                        artYearResourceId = R.string.art4_year,
                        onPreviousClick = {
                            currentStep = 3
                        },
                        onNextClick = {
                            currentStep = 5
                        }
                    )
                }
                5 -> {
                    ArtCard(
                        artNameResourceId = R.string.art5_name,
                        drawableResourceId = R.drawable.img5,
                        artDescriptionResourceId = R.string.art5_description,
                        artAuthorResourceId = R.string.art5_author,
                        artYearResourceId = R.string.art5_year,
                        onPreviousClick = {
                            currentStep = 4
                        },
                        onNextClick = {
                            currentStep = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ArtCard(
    artNameResourceId: Int,
    drawableResourceId: Int,
    artDescriptionResourceId: Int,
    artAuthorResourceId: Int,
    artYearResourceId:Int,
    onPreviousClick: ()-> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(artNameResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(artDescriptionResourceId),
                modifier = Modifier.size(280.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)){
                Button(
                    onClick = onPreviousClick,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A1128)),
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                ){
                    Text(stringResource(R.string.Previous))
                }
                Button(
                    onClick = onNextClick,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A1128)),
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                ){
                    Text(stringResource(R.string.next))
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = stringResource(artAuthorResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(artYearResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun ArtPreview() {
    ArtSpaceTheme() {
        ArtSpaceApp()
    }
}