package com.factumex.prueba.presentation.ui.views



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.compose.ImagePainter
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.factumex.prueba.R


@Preview(showBackground = true)
@Composable
fun ActividadUnoViewPreview() {
    SimpleImageLoader(
        //url = "https://hips.hearstapps.com/hmg-prod/images/gettyimages-1398008130-663c7735a4fd4.jpg?crop=1xw:0.7280716162943496xh;center,top&resize=1200:*",
        url = "",
        name = "Omar Cruz",
        placeholder = painterResource(R.drawable.logo_factum),
        backgroundColor = Color.Blue,
        textColor = Color.White
    )
}


@Composable
fun ActividadUnoView(navHostController: NavHostController) {

    SimpleImageLoader(
        url = "https://hips.hearstapps.com/hmg-prod/images/gettyimages-1398008130-663c7735a4fd4.jpg?crop=1xw:0.7280716162943496xh;center,top&resize=1200:*",
        //url  ="",
        name = "Omar Cruz",
        placeholder = painterResource(R.drawable.logo_factum),
        backgroundColor = Color.Blue,
        textColor = Color.White
    )
/*



    val painter = rememberImagePainter(data = url)
    var initials by remember { mutableStateOf("") }
    var showInitials by remember { mutableStateOf(false) }

    LaunchedEffect(text) {
        if (!text.isNullOrEmpty()) {
            initials = extractInitials(text)
            showInitials = initials.isNotEmpty() && initials[0].isLetter()
        }
    }


    Box(modifier = Modifier.fillMaxSize()){
        Column {
            SimpleImageLoader()
        }
    }*/



}

@Composable
fun SimpleImageLoader(
    url: String,
    name: String,
    placeholder: Painter,
    backgroundColor: Color = Color.Gray,
    textColor: Color = Color.White
) {
    val painter = rememberAsyncImagePainter(model = url)
   // val isImageLoaded = painter.state is AsyncImagePainter.State.Success

    val initials = remember(name) {
        // Extraer iniciales
        val words = name.split(" ")
            .filter { it.isNotEmpty() }
        when {
            words.isEmpty() -> ""
            words.size == 1 -> words[0].take(1)
            else -> words[0].take(1) + words[1].take(1)
        }.uppercase()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(128.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(2.dp, Color.Gray, CircleShape)
    ) {
        when {
            url.isEmpty()  -> {
                if (initials.isNotEmpty() && initials.first().isLetter()) {
                    // Mostrar las iniciales
                    Text(
                        text = initials,
                        color = textColor,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    // Mostrar el placeholder
                    Image(
                        painter = placeholder,
                        contentDescription = "Placeholder",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape)
                    )
                }
            }
            else -> {
                // Mostrar la imagen desde la URL
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = placeholder,
                    contentDescription = "Imagen",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
        }
    }
}

/*
@Composable
fun SimpleImageLoader() {
    val url = "https://hips.hearstapps.com/hmg-prod/images/gettyimages-1398008130-663c7735a4fd4.jpg?crop=1xw:0.7280716162943496xh;center,top&resize=1200:*"
    val painter = rememberImagePainter(data = url)
    val painterState = painter.state

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(128.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.logo_factum),
            contentDescription = "Imagen",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
    }
}
*/