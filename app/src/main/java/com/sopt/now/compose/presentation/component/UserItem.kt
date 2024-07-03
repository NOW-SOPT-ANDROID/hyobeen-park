package com.sopt.now.compose.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun UserItem(
    painter: Int,
    contentDescription: String,
    imageSize: Dp,
    nickname: String,
    phone: String,
    fontSize: TextUnit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = painter),
                contentDescription = contentDescription,
                Modifier
                    .width(imageSize)
                    .height(imageSize)
                    .background(
                        color = Color(0xFF209672),
                        shape = RoundedCornerShape(10.dp)
                    )
            )

            Text(
                text = nickname,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Text(
            text = phone,
            fontSize = fontSize
        )

    }
}

@Composable
fun UserItem(
    painter: String,
    contentDescription: String,
    imageSize: Dp,
    nickname: String,
    phone: String,
    fontSize: TextUnit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(painter),
                contentDescription = contentDescription,
                Modifier
                    .width(imageSize)
                    .height(imageSize)
                    .background(
                        color = Color(0x00FFFFFF),
                        shape = RoundedCornerShape(10.dp)
                    )
            )

            Text(
                text = nickname,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Text(
            text = phone,
            fontSize = fontSize
        )

    }
}