//package com.moviearchive.feature.presentation.home
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.Scaffold
//import androidx.compose.material.ScaffoldState
//import androidx.compose.material.rememberScaffoldState
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.hilt.navigation.compose.hiltViewModel
//
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.rememberAsyncImagePainter
//import coil.request.ImageRequest
//import gmail.adnan9011.moviesample.data.model.Movie
//import gmail.adnan9011.moviesample.ui.theme.HighPadding
//import gmail.adnan9011.moviesample.ui.theme.NormalPadding
//import gmail.adnan9011.moviesample.ui.theme.VeryHighPadding
//
//@Composable
//fun HomeScreen(
//    modifier: Modifier,
//    viewModel: HomeViewModel = hiltViewModel(),
//    scaffoldState: ScaffoldState = rememberScaffoldState(),
//    onShowDetail: (Movie) -> Unit
//) {
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = { }
//    ) { paddingValues ->
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//        HomeContent(
//            color = Color.Black,
//            modifier = modifier.padding(paddingValues),
//            onShowDetail = onShowDetail
//        )
//    }
//}
//
//@Composable
//fun HomeContent(
//    color: Color,
//    modifier: Modifier,
//    onShowDetail: (Movie) -> Unit
//) {
//    Surface(
//        color = color,
//        modifier = modifier.fillMaxWidth()
//    ) {
//        Box() {
//            BackgroundOfScreen(index = 0)
//            Column {
//                Text(
//                    text = "Favorite Movies ",
//                    style = TextStyle(
//                        fontSize = 40.sp,
//                        color = Color.White,
//                        fontFamily = FontFamily.Cursive,
//                        fontWeight = FontWeight.Bold
//                    ),
//                    modifier = Modifier.padding(top = VeryHighPadding, start = HighPadding)
//                )
//                LoadImages(
//                    onShowDetail = onShowDetail
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun BackgroundOfScreen(index: Int) {
//    Image(
//        modifier = Modifier
//            .fillMaxSize()
//            .alpha(0.15f),
//        painter = rememberAsyncImagePainter(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(movies[index].posterLink)
//                .build()
//        ),
//        contentScale = ContentScale.Crop,
//        contentDescription = "Movie Picture"
//    )
//}
//
//@Composable
//fun LoadImages(
//    onShowDetail: (Movie) -> Unit
//) {
//    val scrollState = rememberScrollState()
//
//    LazyRow(
//        Modifier
//            .verticalScroll(scrollState)
//            .padding(top = VeryHighPadding)
//    ) {
//        items(movies.size) {index ->
//            ImageItems(
//                movie = movies[index],
//                onShowDetail = onShowDetail
//            )
//        }
//    }
//}
//
//@Composable
//fun ImageItems(
//    movie: Movie,
//    onShowDetail: (Movie) -> Unit
//) {
//    val context = LocalContext.current
//
//    Column(
//        modifier = Modifier
//            .padding(top = VeryHighPadding, start = HighPadding)
//            .clickable {
//                onShowDetail.invoke(movie)
//            }
//    ) {
//        Image(
//            modifier = Modifier
//                .size(width = 200.dp, height = 300.dp)
//                .clip(RoundedCornerShape(size = 50.dp)),
//            painter = rememberAsyncImagePainter(
//                model = ImageRequest.Builder(context)
//                    .data(movie.posterLink)
//                    .build()
//            ),
//            contentDescription = null
//        )
//        Text(
//            text = movie.title,
//            style = TextStyle(
//                fontSize = 20.sp,
//                color = Color.White,
//                fontFamily = FontFamily.Cursive,
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.padding(top = NormalPadding, start = NormalPadding)
//        )
//    }
//}