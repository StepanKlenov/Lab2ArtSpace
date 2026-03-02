package com.example.artspace

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {

    val gallery = listOf(
        Artwork(R.drawable.photo1, R.string.photo1_title, R.string.photo1_artist, R.string.photo1_year),
        Artwork(R.drawable.photo2, R.string.photo2_title, R.string.photo2_artist, R.string.photo2_year),
        Artwork(R.drawable.photo3, R.string.photo3_title, R.string.photo3_artist, R.string.photo3_year)
    )


    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val currentArtwork = gallery[currentIndex]


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(bottom = 32.dp),
            shadowElevation = 10.dp,
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = currentArtwork.imageResId),
                contentDescription = stringResource(id = currentArtwork.titleResId),
                modifier = Modifier.padding(30.dp),
                contentScale = ContentScale.Fit
            )
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = stringResource(id = currentArtwork.titleResId),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "${stringResource(id = currentArtwork.artistResId)} (${stringResource(id = currentArtwork.yearResId)})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0, // Выключается на первой картинке
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.previous))
            }


            Button(
                onClick = { if (currentIndex < gallery.size - 1) currentIndex++ },
                enabled = currentIndex < gallery.size - 1, // Выключается на последней картинке
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}