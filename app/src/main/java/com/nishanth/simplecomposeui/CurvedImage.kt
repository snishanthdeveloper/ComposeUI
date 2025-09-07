package com.nishanth.simplecomposeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CurvedImage(url: String) {
    val curvedShape = GenericShape { size, _ ->
        moveTo(0f, 0f)
        quadraticBezierTo(size.width / 2, size.height, size.width, 0f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }

    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = "Curved Image",
        modifier = Modifier
            .size(200.dp)
            .clip(curvedShape)
    )
}

@Preview
@Composable
fun previewCurvedImage(){
    CurvedImage("")
}
